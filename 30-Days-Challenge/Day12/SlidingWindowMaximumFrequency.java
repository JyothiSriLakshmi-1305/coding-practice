import java.util.*;

/*
 * Day 12: Sliding Window Maximum with Frequency
 *
 * Aim:
 * Find the maximum value and its frequency in every
 * consecutive window of size W.
 *
 * Problem Statement:
 * Given N resonance values and a window size W, slide
 * the window from left to right. For every window, print
 * the maximum value and the number of times it occurs.
 *
 * Approach:
 * 1. Use a monotonic deque to maintain maximum candidates.
 * 2. Use a HashMap to track frequencies in the window.
 * 3. Remove outgoing elements and update their frequencies.
 * 4. Remove smaller values from the deque's back.
 * 5. Print the maximum and its frequency for each window.
 *
 * Time Complexity: O(N) expected
 * Space Complexity: O(N)
 */

public class SlidingWindowMaximumFrequency {

    public static void userLogic(int n, int w, int[] resonanceValues) {

        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < n; i++) {

            // Remove the element leaving the window
            if (i >= w) {

                int outgoing = resonanceValues[i - w];
                int count = frequency.get(outgoing);

                if (count == 1) {
                    frequency.remove(outgoing);
                } else {
                    frequency.put(outgoing, count - 1);
                }
            }

            // Add the current element's frequency
            int value = resonanceValues[i];

            frequency.put(
                value,
                frequency.getOrDefault(value, 0) + 1
            );

            // Maintain decreasing order in the deque
            while (!deque.isEmpty() &&
                   resonanceValues[deque.peekLast()] < value) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Remove indices outside the current window
            while (!deque.isEmpty() &&
                   deque.peekFirst() <= i - w) {
                deque.pollFirst();
            }

            // Print the result for each complete window
            if (i >= w - 1) {

                int maximum = resonanceValues[deque.peekFirst()];
                int maxFrequency = frequency.get(maximum);

                System.out.println(maximum + " " + maxFrequency);
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int w = scanner.nextInt();

        int[] resonanceValues = new int[n];

        for (int i = 0; i < n; i++) {
            resonanceValues[i] = scanner.nextInt();
        }

        userLogic(n, w, resonanceValues);

        scanner.close();
    }
}


/*
Sample Test Case 0
Input
5 2
7 7 3 9 9


Output
7 2
7 1
9 1
9 2


Explanation: The four windows are [7,7], [7,3], [3,9], and [9,9]. Their maximum values and frequencies are (7,2), (7,1), (9,1), and (9,2).
Sample Test Case 1
Input
6 3
5 1 5 2 5 5


Output
5 2
5 1
5 2
5 2


Explanation: The four windows are [5,1,5], [1,5,2], [5,2,5], and [2,5,5]. The output reports the maximum and its frequency for each window.
Complexity analysis
- Time: (O(N)

*/
