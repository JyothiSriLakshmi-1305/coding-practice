import java.util.*;

/*
 * Day 21: Sliding Window Maximum and Dominant Brightness
 *
 * Problem:
 * Find the maximum brightness in every consecutive window of
 * size K, then identify the maximum value that occurs most often
 * among those window maximums.
 *
 * Tie-breaking:
 * If multiple brightness values have the same highest frequency,
 * choose the smallest brightness value.
 *
 * Approach:
 * 1. Use a monotonic deque to calculate sliding-window maximums.
 * 2. Use a HashMap to count the frequency of each maximum.
 * 3. Select the most frequent maximum, breaking ties by smaller value.
 *
 * Time Complexity: O(N) expected
 * Space Complexity: O(N)
 *
 * Sample Test Case 1
 *
 * Input:
 * 5 2
 * 7 7 1 9 9
 *
 * Output:
 * 7 7 9 9
 * 7 2
 *
 * Sample Test Case 2
 *
 * Input:
 * 6 3
 * 4 3 5 5 2 6
 *
 * Output:
 * 5 5 5 6
 * 5 3
 */

public class SlidingWindowDominantBrightness {

    public static void calculateStandoutBrightness(
            int n, int k, int[] brightness,
            List<Integer> standoutBrightness,
            int[] dominantBrightness) {

        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < n; i++) {

            // Remove indices outside the current window.
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Maintain decreasing brightness in the deque.
            while (!deque.isEmpty() &&
                   brightness[deque.peekLast()] <= brightness[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Record the maximum for every complete window.
            if (i >= k - 1) {
                int maximum = brightness[deque.peekFirst()];
                standoutBrightness.add(maximum);

                frequency.put(
                    maximum,
                    frequency.getOrDefault(maximum, 0) + 1
                );
            }
        }

        int bestValue = Integer.MAX_VALUE;
        int bestCount = 0;

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (count > bestCount ||
                (count == bestCount && value < bestValue)) {
                bestValue = value;
                bestCount = count;
            }
        }

        dominantBrightness[0] = bestValue;
        dominantBrightness[1] = bestCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] brightness = new int[n];

        for (int i = 0; i < n; i++) {
            brightness[i] = scanner.nextInt();
        }

        List<Integer> standoutBrightness = new ArrayList<>();
        int[] dominantBrightness = new int[2];

        calculateStandoutBrightness(
            n, k, brightness,
            standoutBrightness, dominantBrightness
        );

        for (int i = 0; i < standoutBrightness.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(standoutBrightness.get(i));
        }
        System.out.println();

        System.out.println(
            dominantBrightness[0] + " " + dominantBrightness[1]
        );

        scanner.close();
    }
}
