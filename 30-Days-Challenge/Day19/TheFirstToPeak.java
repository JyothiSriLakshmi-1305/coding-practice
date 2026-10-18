import java.util.*;

/*
 * Day 19: The First to Peak
 *
 * Problem:
 * For every sliding window of size K, find the earliest
 * reading with the maximum temperature.
 * Count how many windows each sensor wins.
 *
 * Approach:
 * 1. Use a monotonic deque to find window maximums.
 * 2. Preserve equal temperatures so the earliest maximum wins.
 * 3. Use a HashMap to count each sensor's credits.
 * 4. Sort by credit count descending, then sensor ID ascending.
 *
 * Time Complexity: O(N + D log D)
 * Space Complexity: O(N + D)
 *
 * Sample Test Case 1
 *
 * Input:
 * 6 3
 * 10 5 20 9 30 9 40 3 50 9 60 1
 *
 * Output:
 * 3
 * 20 2
 * 30 1
 * 50 1
 *
 * Sample Test Case 2
 *
 * Input:
 * 5 2
 * 7 100 7 100 8 50 9 200 9 200
 *
 * Output:
 * 2
 * 7 2
 * 9 2
 */

public class TheFirstToPeak {

    public static void calculateSensorCredits(
            int N, int K, List<int[]> readings) {

        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> credits = new HashMap<>();

        for (int i = 0; i < N; i++) {

            // Remove readings outside the current window.
            while (!deque.isEmpty() && deque.peekFirst() <= i - K) {
                deque.pollFirst();
            }

            // Remove strictly smaller temperatures.
            // Equal temperatures retain the earlier reading.
            while (!deque.isEmpty() &&
                   readings.get(deque.peekLast())[1] <
                   readings.get(i)[1]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Award credit to the earliest maximum in this window.
            if (i >= K - 1) {
                int sensorId = readings.get(deque.peekFirst())[0];

                credits.put(
                    sensorId,
                    credits.getOrDefault(sensorId, 0) + 1
                );
            }
        }

        List<int[]> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : credits.entrySet()) {
            result.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // Credits descending; sensor ID ascending for ties.
        result.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        System.out.println(result.size());

        for (int[] sensor : result) {
            System.out.println(sensor[0] + " " + sensor[1]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        List<int[]> readings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int sensorId = scanner.nextInt();
            int temperature = scanner.nextInt();

            readings.add(new int[]{sensorId, temperature});
        }

        calculateSensorCredits(N, K, readings);
        scanner.close();
    }
}
