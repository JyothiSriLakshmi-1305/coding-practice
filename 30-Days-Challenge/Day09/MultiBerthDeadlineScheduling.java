/*
 * Day 09: Multi-Berth Deadline Scheduling
 *
 * Problem:
 * Schedule requests within their deadlines when each day
 * has multiple available berths. Maximize total priority
 * and record the assigned day for every request.
 *
 * Approach:
 * Greedy + Sorting + Disjoint Set Union (DSU)
 *
 * Time Complexity: O(T log T)
 * Space Complexity: O(D + T)
 */

import java.util.*;

public class MultiBerthDeadlineScheduling {

    static int[] parent;
    static long maxProfit;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void userLogic(int D, int T, int[] berths,
                                 int[][] requests, int[] result) {

        Integer[] order = new Integer[T];

        for (int i = 0; i < T; i++) {
            order[i] = i;
        }

        // Higher priority first.
        // For equal priority, earlier input first.
        Arrays.sort(order, (a, b) -> {
            if (requests[a][1] != requests[b][1]) {
                return Integer.compare(requests[b][1], requests[a][1]);
            }

            return Integer.compare(a, b);
        });

        // Initialize DSU
        parent = new int[D + 1];

        for (int i = 0; i <= D; i++) {
            parent[i] = i;
        }

        int[] remaining = berths.clone();

        // Remove days with zero berths.
        for (int day = 1; day <= D; day++) {
            if (remaining[day] == 0) {
                parent[day] = find(day - 1);
            }
        }

        maxProfit = 0;

        // Process requests by decreasing priority.
        for (int index : order) {

            int deadline = requests[index][0];
            int priority = requests[index][1];

            // Find latest available day <= deadline.
            int day = find(deadline);

            if (day > 0) {

                // Store assignment in original request order.
                result[index] = day;

                maxProfit += priority;
                remaining[day]--;

                // Remove the day when all berths are occupied.
                if (remaining[day] == 0) {
                    parent[day] = find(day - 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int D = sc.nextInt();
        int T = sc.nextInt();

        int[] berths = new int[D + 1];

        for (int i = 1; i <= D; i++) {
            berths[i] = sc.nextInt();
        }

        int[][] requests = new int[T][2];

        for (int i = 0; i < T; i++) {
            requests[i][0] = sc.nextInt();
            requests[i][1] = sc.nextInt();
        }

        int[] result = new int[T];

        userLogic(D, T, berths, requests, result);

        // Maximum total importance.
        System.out.println(maxProfit);

        // Assignments in original input order.
        for (int i = 0; i < T; i++) {
            System.out.print(result[i] + " ");
        }

        System.out.println();

        sc.close();
    }
}

/*
 * Sample Test Case 1:
 *
 * Input:
 * 3 4
 * 1 1 1
 * 2 100
 * 1 10
 * 2 15
 * 3 27
 *
 * Output:
 * 142
 * 2 0 1 3
 *
 * Sample Test Case 2:
 *
 * Input:
 * 2 3
 * 2 1
 * 1 50
 * 2 40
 * 2 30
 *
 * Output:
 * 120
 * 1 1 2
 */
