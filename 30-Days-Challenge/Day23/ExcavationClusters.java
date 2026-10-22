import java.util.*;

/*
 * Day 23: Excavation Clusters and Artifact Categories
 *
 * Problem:
 * Process candidate tunnels in increasing cost order.
 * Dig a tunnel only when it connects two different components.
 * Report:
 * 1. Total digging cost.
 * 2. Size of the largest component.
 * 3. Most frequent category in that component.
 * 4. Frequency of that category.
 *
 * Tie-breaking:
 * - Largest component: smallest numbered zone in the component.
 * - Most frequent category: smallest category code.
 *
 * Approach:
 * 1. Kruskal's algorithm sorts and processes the tunnels.
 * 2. DSU maintains connected components.
 * 3. Find the largest component after all edges are processed.
 * 4. Count categories in that component.
 *
 * Time Complexity: O(M log M + (N + M) alpha(N))
 * Space Complexity: O(N + M)
 *
 * Sample Test Case 1
 *
 * Input:
 * 5 4
 * 10 10 20 20 10
 * 1 2 5
 * 2 3 8
 * 3 4 3
 * 4 5 7
 *
 * Output:
 * 23 5 10 3
 *
 * Sample Test Case 2
 *
 * Input:
 * 6 3
 * 5 5 5 7 7 7
 * 1 2 4
 * 2 3 6
 * 4 5 2
 *
 * Output:
 * 12 3 5 3
 */

public class ExcavationClusters {

    static class DSU {
        int[] parent;
        int[] size;
        int[] minimumZone;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            minimumZone = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
                minimumZone[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            minimumZone[rootA] = Math.min(
                minimumZone[rootA], minimumZone[rootB]
            );

            return true;
        }
    }

    static class Edge {
        int u;
        int v;
        int cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] category = new int[n];

        for (int i = 0; i < n; i++) {
            category[i] = scanner.nextInt();
        }

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(
                scanner.nextInt(),
                scanner.nextInt(),
                scanner.nextInt()
            );
        }

        Arrays.sort(edges, (a, b) ->
            Integer.compare(a.cost, b.cost)
        );

        DSU dsu = new DSU(n);
        long totalCost = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.u, edge.v)) {
                totalCost += edge.cost;
            }
        }

        int largestRoot = -1;
        int largestSize = 0;
        int smallestZone = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            if (dsu.find(i) == i) {
                if (dsu.size[i] > largestSize ||
                    (dsu.size[i] == largestSize &&
                     dsu.minimumZone[i] < smallestZone)) {

                    largestSize = dsu.size[i];
                    smallestZone = dsu.minimumZone[i];
                    largestRoot = i;
                }
            }
        }

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            if (dsu.find(i) == largestRoot) {
                frequency.put(
                    category[i - 1],
                    frequency.getOrDefault(category[i - 1], 0) + 1
                );
            }
        }

        int bestCategory = Integer.MAX_VALUE;
        int bestFrequency = 0;

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (count > bestFrequency ||
                (count == bestFrequency && value < bestCategory)) {
                bestCategory = value;
                bestFrequency = count;
            }
        }

        System.out.println(
            totalCost + " " + largestSize + " " +
            bestCategory + " " + bestFrequency
        );

        scanner.close();
    }
}
