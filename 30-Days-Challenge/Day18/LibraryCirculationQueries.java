import java.util.*;

/*
 * Day 18: Library Circulation Queries
 *
 * Problem:
 * Process checkout, return, and inspection operations.
 * For each inspection, count currently checked-out books
 * whose popularity score is strictly less than the threshold.
 *
 * Approach:
 * 1. Coordinate compression maps scores to sorted ranks.
 * 2. HashMap stores each checked-out book's current score.
 * 3. Fenwick Tree maintains the frequency of each score.
 * 4. Binary search finds the number of scores below a threshold.
 *
 * Time Complexity: O(M log M)
 * Space Complexity: O(M)
 *
 * Sample Test Case 1
 *
 * Input:
 * 6
 * 1 10 500
 * 1 11 500
 * 3 100
 * 2 10
 * 3 500
 * 3 1000
 *
 * Output:
 * 0
 * 0
 * 1
 *
 * Sample Test Case 2
 *
 * Input:
 * 7
 * 1 501 40
 * 1 502 70
 * 3 50
 * 2 501
 * 1 503 20
 * 3 50
 * 3 100
 *
 * Output:
 * 1
 * 1
 * 2
 */

public class LibraryCirculationQueries {

    public static List<Integer> userLogic(List<int[]> moments) {
        List<Integer> results = new ArrayList<>();
        TreeSet<Integer> scoreSet = new TreeSet<>();

        // Collect all scores and thresholds for coordinate compression.
        for (int[] moment : moments) {
            if (moment[0] == 1) {
                scoreSet.add(moment[2]);
            } else if (moment[0] == 3) {
                scoreSet.add(moment[1]);
            }
        }

        List<Integer> scores = new ArrayList<>(scoreSet);
        Map<Integer, Integer> rank = new HashMap<>();

        for (int i = 0; i < scores.size(); i++) {
            rank.put(scores.get(i), i + 1);
        }

        FenwickTree fenwick = new FenwickTree(scores.size());
        Map<Integer, Integer> checkedOut = new HashMap<>();

        for (int[] moment : moments) {
            int type = moment[0];

            if (type == 1) {
                int id = moment[1];
                int score = moment[2];

                checkedOut.put(id, score);
                fenwick.update(rank.get(score), 1);

            } else if (type == 2) {
                int id = moment[1];
                int score = checkedOut.remove(id);

                fenwick.update(rank.get(score), -1);

            } else if (type == 3) {
                int threshold = moment[1];
                int count = fenwick.query(
                    lowerBound(scores, threshold)
                );

                results.add(count);
            }
        }

        return results;
    }

    private static int lowerBound(
            List<Integer> scores, int threshold) {

        int left = 0;
        int right = scores.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores.get(mid) < threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static class FenwickTree {
        int[] tree;
        int n;

        FenwickTree(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int delta) {
            while (index <= n) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        int query(int index) {
            int sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();

        List<int[]> moments = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int type = scanner.nextInt();

            if (type == 1) {
                int id = scanner.nextInt();
                int score = scanner.nextInt();
                moments.add(new int[]{type, id, score});
            } else {
                int value = scanner.nextInt();
                moments.add(new int[]{type, value});
            }
        }

        List<Integer> results = userLogic(moments);

        for (int result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}
