/*
 * Day 10: Minimax Path
 *
 * Problem:
 * Find the minimum possible value of the maximum edge weight
 * on a path from node 1 to every other node.
 *
 * Approach:
 * Modified Dijkstra's Algorithm
 *
 * Instead of adding edge weights, the path cost is:
 * max(current path risk, edge weight)
 *
 * Time Complexity: O((n + m) log n)
 * Space Complexity: O(n + m)
 */

import java.util.*;

public class MinimaxPath {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        long risk;

        Node(int vertex, long risk) {
            this.vertex = vertex;
            this.risk = risk;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.risk, other.risk);
        }
    }

    public static void calculateRiskiestTube(int n, int m,
                                             List<int[]> edges) {

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        long[] risk = new long[n + 1];

        Arrays.fill(risk, Long.MAX_VALUE);

        risk[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            int u = current.vertex;
            long currentRisk = current.risk;

            // Ignore outdated priority queue entries
            if (currentRisk != risk[u]) {
                continue;
            }

            for (Edge edge : graph.get(u)) {

                int v = edge.to;
                int weight = edge.weight;

                long newRisk = Math.max(currentRisk, weight);

                if (newRisk < risk[v]) {
                    risk[v] = newRisk;
                    pq.offer(new Node(v, newRisk));
                }
            }
        }

        // Print results
        for (int i = 1; i <= n; i++) {

            if (risk[i] == Long.MAX_VALUE) {
                System.out.print("-1");
            } else {
                System.out.print(risk[i]);
            }

            if (i < n) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {

            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            edges.add(new int[]{u, v, w});
        }

        calculateRiskiestTube(n, m, edges);

        scanner.close();
    }
}

/*
 * Sample Test Case 1:
 *
 * Input:
 * 4 4
 * 1 2 5
 * 2 3 3
 * 1 3 10
 * 3 4 7
 *
 * Output:
 * 0 5 5 7
 *
 * Sample Test Case 2:
 *
 * Input:
 * 3 1
 * 2 3 4
 *
 * Output:
 * 0 -1 -1
 */
