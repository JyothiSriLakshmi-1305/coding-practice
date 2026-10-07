/*
 * Day 08: Job Sequencing with Deadlines
 *
 * Problem:
 * Each order has a profit and a deadline. Each order takes
 * exactly one slot and must be completed on or before its
 * deadline. Find the maximum possible profit and the number
 * of accepted orders.
 *
 * Approach:
 * Greedy + Sorting + Disjoint Set Union (DSU)
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class JobSequencingWithDeadlines {

    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void calculateMaxProfit(int[][] orders, int n, Result result) {

        // Process higher-profit orders first
        Arrays.sort(orders, (a, b) -> Integer.compare(b[0], a[0]));

        // Slots 0 to n
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        long totalProfit = 0;
        int accepted = 0;

        for (int[] order : orders) {

            int profit = order[0];
            int deadline = Math.min(order[1], n);

            // Find the latest available slot <= deadline
            int slot = find(deadline);

            if (slot > 0) {
                totalProfit += profit;
                accepted++;

                // Mark the slot as occupied
                parent[slot] = find(slot - 1);
            }
        }

        result.maxProfit = totalProfit;
        result.numAcceptedOrders = accepted;
    }

    static class Result {
        long maxProfit = 0;
        int numAcceptedOrders = 0;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[][] orders = new int[n][2];

        for (int i = 0; i < n; i++) {
            orders[i][0] = scanner.nextInt();
            orders[i][1] = scanner.nextInt();
        }

        Result result = new Result();

        calculateMaxProfit(orders, n, result);

        System.out.println(result.maxProfit + " " + result.numAcceptedOrders);

        scanner.close();
    }
}

/*
 * Sample Test Case 1:
 *
 * Input:
 * 4
 * 100 1000000000
 * 80 2
 * 60 2
 * 40 1
 *
 * Output:
 * 240 3
 *
 * Sample Test Case 2:
 *
 * Input:
 * 3
 * 50 1
 * 50 2
 * 50 3
 *
 * Output:
 * 150 3
 */
