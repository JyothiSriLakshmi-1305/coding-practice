
/*
 * Day 05: Next Greater Team Rating
 *
 * Problem:
 * For each entry, find the next later entry belonging
 * to the same team with a strictly greater rating.
 * Calculate the waiting distance and select the top K
 * entries based on waiting distance.
 *
 * Approach:
 * HashMap + Monotonic Stack + Sorting
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class NextGreaterTeamRating {

    public static void userLogic(int n, int K, int[][] entries,
                                 int[] waitValues,
                                 List<Integer> leaderboardPositions) {

        Arrays.fill(waitValues, -1);

        // Group entry indices by team
        Map<Integer, List<Integer>> teamMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            teamMap.computeIfAbsent(entries[i][0], k -> new ArrayList<>()).add(i);
        }

        // Find the next greater rating for each team
        for (List<Integer> indices : teamMap.values()) {

            Stack<Integer> stack = new Stack<>();

            for (int j = indices.size() - 1; j >= 0; j--) {

                int current = indices.get(j);
                int currentRating = entries[current][1];

                while (!stack.isEmpty() &&
                       entries[stack.peek()][1] <= currentRating) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    waitValues[current] = stack.peek() - current;
                }

                stack.push(current);
            }
        }

        // Collect entries having finite waiting values
        List<Integer> candidates = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (waitValues[i] != -1) {
                candidates.add(i);
            }
        }

        // Sort by waiting value descending, then position ascending
        candidates.sort((a, b) -> {
            if (waitValues[a] != waitValues[b]) {
                return Integer.compare(waitValues[b], waitValues[a]);
            }

            return Integer.compare(a, b);
        });

        // Select up to K positions
        for (int i = 0; i < Math.min(K, candidates.size()); i++) {
            leaderboardPositions.add(candidates.get(i) + 1);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int K = scanner.nextInt();

        int[][] entries = new int[n][2];

        for (int i = 0; i < n; i++) {
            entries[i][0] = scanner.nextInt();
            entries[i][1] = scanner.nextInt();
        }

        int[] waitValues = new int[n];

        List<Integer> leaderboardPositions = new ArrayList<>();

        userLogic(n, K, entries, waitValues, leaderboardPositions);

        // Print waiting values
        for (int i = 0; i < n; i++) {
            System.out.print(waitValues[i] + " ");
        }
        System.out.println();

        // Print leaderboard positions
        for (int pos : leaderboardPositions) {
            System.out.print(pos + " ");
        }
        System.out.println();

        scanner.close();
    }
}

/*
 * Sample Test Case 1:
 *
 * Input:
 * 8 3
 * 3 10
 * 9 8
 * 3 5
 * 3 12
 * 9 9
 * 3 7
 * 9 15
 * 3 20
 *
 * Output:
 * 3 3 1 4 2 2 -1 -1
 * 4 1 2
 *
 * Sample Test Case 2:
 *
 * Input:
 * 5 5
 * 1 100
 * 1 50
 * 1 90
 * 2 5
 * 2 5
 *
 * Output:
 * -1 1 -1 -1 -1
 * 2
 */
