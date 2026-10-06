/*
 * Day 07: Range Maximum Frequency Query
 *
 * Problem:
 * Given an array and multiple range queries, find the
 * maximum frequency of any element in each range.
 *
 * Approach:
 * Mo's Algorithm + Coordinate Compression
 *
 * Time Complexity: O((n + q) * sqrt(n) + q log q)
 * Space Complexity: O(n + q)
 */

import java.util.*;

public class RangeMaximumFrequencyQuery {

    static class Query {
        int l, r, index, block;

        Query(int l, int r, int index, int block) {
            this.l = l;
            this.r = r;
            this.index = index;
            this.block = block;
        }
    }

    public static void userLogic(int n, int q, int[] collections,
                                 int[][] stretches, int[] results) {

        // Coordinate compression
        int[] sorted = collections.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> compressedMap = new HashMap<>();
        int uniqueCount = 0;

        for (int value : sorted) {
            if (!compressedMap.containsKey(value)) {
                compressedMap.put(value, uniqueCount++);
            }
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = compressedMap.get(collections[i]);
        }

        // Create queries
        int blockSize = Math.max(1, (int) Math.sqrt(n));
        Query[] queries = new Query[q];

        for (int i = 0; i < q; i++) {
            int l = stretches[i][0] - 1;
            int r = stretches[i][1] - 1;

            queries[i] = new Query(l, r, i, l / blockSize);
        }

        // Sort queries using Mo's ordering
        Arrays.sort(queries, (a, b) -> {
            if (a.block != b.block) {
                return Integer.compare(a.block, b.block);
            }

            if (a.block % 2 == 0) {
                return Integer.compare(a.r, b.r);
            }

            return Integer.compare(b.r, a.r);
        });

        int[] frequency = new int[uniqueCount];
        int[] frequencyCount = new int[n + 1];

        int currentL = 0;
        int currentR = -1;
        int maxFrequency = 0;

        for (Query query : queries) {

            while (currentL > query.l) {
                currentL--;

                int value = arr[currentL];
                int oldFreq = frequency[value];

                if (oldFreq > 0) {
                    frequencyCount[oldFreq]--;
                }

                int newFreq = ++frequency[value];
                frequencyCount[newFreq]++;

                maxFrequency = Math.max(maxFrequency, newFreq);
            }

            while (currentR < query.r) {
                currentR++;

                int value = arr[currentR];
                int oldFreq = frequency[value];

                if (oldFreq > 0) {
                    frequencyCount[oldFreq]--;
                }

                int newFreq = ++frequency[value];
                frequencyCount[newFreq]++;

                maxFrequency = Math.max(maxFrequency, newFreq);
            }

            while (currentL < query.l) {
                int value = arr[currentL];
                int oldFreq = frequency[value];

                frequencyCount[oldFreq]--;
                int newFreq = --frequency[value];

                if (newFreq > 0) {
                    frequencyCount[newFreq]++;
                }

                if (oldFreq == maxFrequency &&
                    frequencyCount[oldFreq] == 0) {
                    maxFrequency--;
                }

                currentL++;
            }

            while (currentR > query.r) {
                int value = arr[currentR];
                int oldFreq = frequency[value];

                frequencyCount[oldFreq]--;
                int newFreq = --frequency[value];

                if (newFreq > 0) {
                    frequencyCount[newFreq]++;
                }

                if (oldFreq == maxFrequency &&
                    frequencyCount[oldFreq] == 0) {
                    maxFrequency--;
                }

                currentR--;
            }

            results[query.index] = maxFrequency;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int q = scanner.nextInt();

        int[] collections = new int[n];

        for (int i = 0; i < n; i++) {
            collections[i] = scanner.nextInt();
        }

        int[][] stretches = new int[q][2];

        for (int i = 0; i < q; i++) {
            stretches[i][0] = scanner.nextInt();
            stretches[i][1] = scanner.nextInt();
        }

        int[] results = new int[q];

        userLogic(n, q, collections, stretches, results);

        for (int result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}

/*
 * Sample Test Case 1:
 *
 * Input:
 * 8 3
 * 5 5 5 5 6 6 7 7
 * 1 2
 * 1 8
 * 5 8
 *
 * Output:
 * 2
 * 4
 * 2
 *
 * Sample Test Case 2:
 *
 * Input:
 * 6 2
 * 10 20 10 10 30 20
 * 1 4
 * 3 6
 *
 * Output:
 * 3
 * 2
 */
