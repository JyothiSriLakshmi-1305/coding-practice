```java
import java.util.*;

/*
 * Day 11: Dynamic Range Queries
 *
 * Problem:
 * Given an array of exposure scores, process three types of events:
 *
 * 1. RANK l r k
 *    Find the k-th smallest exposure score in positions l to r.
 *
 * 2. FLAG i
 *    Toggle the flagged status of position i.
 *
 * 3. AUDIT l r
 *    Count how many positions from l to r are currently flagged.
 *
 * Approach:
 * - Coordinate Compression converts exposure scores into compact indices.
 * - Persistent Segment Tree answers k-th smallest range queries.
 * - Fenwick Tree handles dynamic flag updates and range-count queries.
 *
 * Time Complexity:
 * - Building Persistent Segment Tree: O(N log N)
 * - RANK query: O(log N)
 * - FLAG update: O(log N)
 * - AUDIT query: O(log N)
 * - Overall: O((N + Q) log N)
 *
 * Space Complexity:
 * O(N log N)
 */

public class DynamicRangeQueries {

    // Fenwick Tree / Binary Indexed Tree
    static class FenwickTree {

        int[] tree;

        FenwickTree(int n) {
            tree = new int[n + 1];
        }

        // Add delta to a position
        void update(int index, int delta) {

            while (index < tree.length) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        // Prefix sum from 1 to index
        int query(int index) {

            int sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }

            return sum;
        }

        // Range sum from left to right
        int rangeQuery(int left, int right) {
            return query(right) - query(left - 1);
        }
    }

    // Node of Persistent Segment Tree
    static class Node {

        int left;
        int right;
        int count;

        Node(int left, int right, int count) {
            this.left = left;
            this.right = right;
            this.count = count;
        }
    }

    static List<Node> segmentTree;

    /*
     * Creates a new version of the segment tree after inserting
     * one exposure score.
     */
    static int update(int previous, int start, int end, int position) {

        int current = segmentTree.size();

        Node oldNode = segmentTree.get(previous);

        segmentTree.add(
            new Node(
                oldNode.left,
                oldNode.right,
                oldNode.count + 1
            )
        );

        // Leaf node
        if (start == end) {
            return current;
        }

        int mid = (start + end) / 2;

        if (position <= mid) {

            int newLeft = update(
                oldNode.left,
                start,
                mid,
                position
            );

            segmentTree.get(current).left = newLeft;

        } else {

            int newRight = update(
                oldNode.right,
                mid + 1,
                end,
                position
            );

            segmentTree.get(current).right = newRight;
        }

        return current;
    }

    /*
     * Finds the k-th smallest value between two versions
     * of the Persistent Segment Tree.
     */
    static int kthSmallest(
            int leftRoot,
            int rightRoot,
            int start,
            int end,
            int k) {

        // Only one value remains
        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        int rightLeftChild =
                segmentTree.get(rightRoot).left;

        int leftLeftChild =
                segmentTree.get(leftRoot).left;

        int leftCount =
                segmentTree.get(rightLeftChild).count
                -
                segmentTree.get(leftLeftChild).count;

        if (k <= leftCount) {

            return kthSmallest(
                segmentTree.get(leftRoot).left,
                segmentTree.get(rightRoot).left,
                start,
                mid,
                k
            );
        }

        return kthSmallest(
            segmentTree.get(leftRoot).right,
            segmentTree.get(rightRoot).right,
            mid + 1,
            end,
            k - leftCount
        );
    }

    public static void processEvents(
            int N,
            int Q,
            int[] exposureScores,
            List<String[]> events,
            List<String> results) {

        /*
         * ----------------------------------------------------
         * STEP 1: Coordinate Compression
         * ----------------------------------------------------
         */

        int[] sortedValues = exposureScores.clone();

        Arrays.sort(sortedValues);

        int uniqueCount = 0;

        for (int value : sortedValues) {

            if (uniqueCount == 0 ||
                sortedValues[uniqueCount - 1] != value) {

                sortedValues[uniqueCount] = value;
                uniqueCount++;
            }
        }

        /*
         * ----------------------------------------------------
         * STEP 2: Build Persistent Segment Tree
         * ----------------------------------------------------
         */

        segmentTree = new ArrayList<>();

        // Node 0 represents an empty tree
        segmentTree.add(new Node(0, 0, 0));

        // roots[i] represents the first i elements
        int[] roots = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            int compressedPosition = Arrays.binarySearch(
                sortedValues,
                0,
                uniqueCount,
                exposureScores[i - 1]
            );

            roots[i] = update(
                roots[i - 1],
                0,
                uniqueCount - 1,
                compressedPosition
            );
        }

        /*
         * ----------------------------------------------------
         * STEP 3: Fenwick Tree for Flagged Positions
         * ----------------------------------------------------
         */

        FenwickTree fenwick = new FenwickTree(N);

        boolean[] flagged = new boolean[N + 1];

        /*
         * ----------------------------------------------------
         * STEP 4: Process Events
         * ----------------------------------------------------
         */

        for (String[] event : events) {

            String type = event[0];

            /*
             * RANK l r k
             */
            if (type.equals("RANK")) {

                int left = Integer.parseInt(event[1]);
                int right = Integer.parseInt(event[2]);
                int k = Integer.parseInt(event[3]);

                int compressedIndex = kthSmallest(
                    roots[left - 1],
                    roots[right],
                    0,
                    uniqueCount - 1,
                    k
                );

                int answer = sortedValues[compressedIndex];

                results.add(String.valueOf(answer));
            }

            /*
             * FLAG i
             */
            else if (type.equals("FLAG")) {

                int index = Integer.parseInt(event[1]);

                if (flagged[index]) {

                    // Unflag the instrument
                    flagged[index] = false;
                    fenwick.update(index, -1);

                } else {

                    // Flag the instrument
                    flagged[index] = true;
                    fenwick.update(index, 1);
                }
            }

            /*
             * AUDIT l r
             */
            else if (type.equals("AUDIT")) {

                int left = Integer.parseInt(event[1]);
                int right = Integer.parseInt(event[2]);

                int count = fenwick.rangeQuery(left, right);

                results.add(String.valueOf(count));
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        /*
         * Read exposure scores
         */
        int[] exposureScores = new int[N];

        for (int i = 0; i < N; i++) {
            exposureScores[i] = scanner.nextInt();
        }

        /*
         * Read all events
         */
        List<String[]> events = new ArrayList<>();

        for (int i = 0; i < Q; i++) {

            String eventType = scanner.next();

            if (eventType.equals("RANK")) {

                int l = scanner.nextInt();
                int r = scanner.nextInt();
                int k = scanner.nextInt();

                events.add(
                    new String[]{
                        eventType,
                        String.valueOf(l),
                        String.valueOf(r),
                        String.valueOf(k)
                    }
                );

            } else if (eventType.equals("FLAG")) {

                int index = scanner.nextInt();

                events.add(
                    new String[]{
                        eventType,
                        String.valueOf(index)
                    }
                );

            } else if (eventType.equals("AUDIT")) {

                int l = scanner.nextInt();
                int r = scanner.nextInt();

                events.add(
                    new String[]{
                        eventType,
                        String.valueOf(l),
                        String.valueOf(r)
                    }
                );
            }
        }

        /*
         * Store answers here
         */
        List<String> results = new ArrayList<>();

        processEvents(
            N,
            Q,
            exposureScores,
            events,
            results
        );

        /*
         * Print all query answers
         */
        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}
```

### Sample 0

```text
5 3
30 10 20 50 40
RANK 1 3 2
RANK 1 5 1
RANK 3 5 2
```

Output:

```text
20
10
40
```

### Sample 1

```text
4 6
15 42 8 23
FLAG 1
FLAG 3
AUDIT 1 4
RANK 1 4 3
FLAG 1
AUDIT 1 4
```

Output:

```text
2
23
1
```
