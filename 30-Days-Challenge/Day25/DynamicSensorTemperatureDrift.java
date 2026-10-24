import java.util.*;

public class DynamicSensorTemperatureDrift {

    /*
     * Problem: Dynamic Sensor Temperature Drift
     *
     * Approach:
     * 1. Coordinate-compress all reported temperatures.
     * 2. Use a HashMap to track each station's latest temperature.
     * 3. Use a Fenwick Tree to maintain frequencies of current readings.
     * 4. Remove the station's previous reading before counting.
     * 5. A drift occurs when the absolute temperature difference > D.
     * 6. Count current readings strictly less than the new temperature.
     * 7. Insert the new reading and update the station's temperature.
     *
     * Time Complexity: O(q log q)
     * Space Complexity: O(q)
     */

    static class FenwickTree {
        private final int[] tree;
        private final int n;

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

    static int lowerBound(long[] values, int size, long target) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (values[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static List<long[]> processReports(
            int q, long D, List<long[]> reports) {

        List<long[]> result = new ArrayList<>();

        long[] sortedTemps = new long[q];

        for (int i = 0; i < q; i++) {
            sortedTemps[i] = reports.get(i)[1];
        }

        Arrays.sort(sortedTemps);

        int uniqueCount = 0;

        for (int i = 0; i < q; i++) {
            if (uniqueCount == 0
                    || sortedTemps[i] != sortedTemps[uniqueCount - 1]) {
                sortedTemps[uniqueCount++] = sortedTemps[i];
            }
        }

        FenwickTree fenwick = new FenwickTree(uniqueCount);
        Map<Long, Long> latestTemp = new HashMap<>();

        for (long[] report : reports) {
            long id = report[0];
            long newTemp = report[1];

            boolean hasPrevious = latestTemp.containsKey(id);
            long oldTemp = 0;

            if (hasPrevious) {
                oldTemp = latestTemp.get(id);
                int oldRank = lowerBound(
                        sortedTemps, uniqueCount, oldTemp) + 1;
                fenwick.update(oldRank, -1);
            }

            if (hasPrevious && Math.abs(newTemp - oldTemp) > D) {
                int count = lowerBound(
                        sortedTemps, uniqueCount, newTemp);

                long lowerCount = fenwick.query(count);

                result.add(new long[]{id, newTemp, lowerCount});
            }

            int newRank = lowerBound(
                    sortedTemps, uniqueCount, newTemp) + 1;
            fenwick.update(newRank, 1);

            latestTemp.put(id, newTemp);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int q = scanner.nextInt();
        long D = scanner.nextLong();

        List<long[]> reports = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            long id = scanner.nextLong();
            long temp = scanner.nextLong();

            reports.add(new long[]{id, temp});
        }

        List<long[]> result = processReports(q, D, reports);

        for (long[] entry : result) {
            System.out.println(
                    entry[0] + " " + entry[1] + " " + entry[2]);
        }

        scanner.close();
    }
}

/*
Sample Input:
5 5
1 10
2 20
1 17
3 5
2 8

Sample Output:
1 17 0
2 8 1
*/
