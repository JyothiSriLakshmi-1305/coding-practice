import java.util.*;

/*
 * Day 16: Budget-Based Connectivity Queries
 *
 * Aim:
 * Determine the number of wards reachable from a given ward
 * using only roads whose costs do not exceed a budget cap.
 *
 * Problem Statement:
 * Given N wards, E candidate roads, and Q budget scenarios,
 * answer how many wards are connected to the specified ward
 * through roads with costs <= the scenario's budget cap.
 * The starting ward is included in the count.
 *
 * Approach:
 * 1. Map ward names to integer indices using a HashMap.
 * 2. Sort candidate roads by ascending repair cost.
 * 3. Sort scenarios by ascending budget, preserving their
 *    original positions.
 * 4. Use DSU with path compression and union by size.
 * 5. Add eligible roads incrementally for each scenario.
 * 6. Record the component size in the original query order.
 *
 * Time Complexity:
 * O(E log E + Q log Q + (E + Q) alpha(N))
 *
 * Space Complexity:
 * O(N + E + Q)
 */

public class BudgetBasedConnectivityQueries {

    // Disjoint Set Union
    static class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {

            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // Find representative with path compression
        int find(int x) {

            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        // Merge components using union by size
        void union(int a, int b) {

            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        // Number of wards in the component containing x
        int componentSize(int x) {
            return size[find(x)];
        }
    }

    public static List<Integer> user_logic(
            int n,
            int e,
            int q,
            List<String> ward_names,
            List<Tuple> candidate_links,
            List<Pair> scenarios) {

        List<Integer> result = new ArrayList<>(
            Collections.nCopies(q, 0)
        );

        // Map each ward name to a unique integer index
        Map<String, Integer> wardIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            wardIndex.put(ward_names.get(i), i);
        }

        // Sort roads by ascending repair cost
        candidate_links.sort(
            Comparator.comparingInt(link -> link.cost)
        );

        // Store scenario indices to preserve original output order
        Integer[] order = new Integer[q];

        for (int i = 0; i < q; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
            Integer.compare(
                scenarios.get(a).cap,
                scenarios.get(b).cap
            )
        );

        DSU dsu = new DSU(n);

        int edgeIndex = 0;

        // Process scenarios from the smallest budget to the largest
        for (int queryIndex : order) {

            Pair scenario = scenarios.get(queryIndex);
            int cap = scenario.cap;

            // Merge every road affordable under this budget
            while (edgeIndex < e &&
                   candidate_links.get(edgeIndex).cost <= cap) {

                Tuple link = candidate_links.get(edgeIndex);

                int u = wardIndex.get(link.ward1);
                int v = wardIndex.get(link.ward2);

                dsu.union(u, v);

                edgeIndex++;
            }

            int start = wardIndex.get(scenario.ward_name);

            // Include the starting ward in the component size
            result.set(
                queryIndex,
                dsu.componentSize(start)
            );
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int e = scanner.nextInt();
        int q = scanner.nextInt();

        List<String> ward_names = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            ward_names.add(scanner.next());
        }

        List<Tuple> candidate_links = new ArrayList<>();

        for (int i = 0; i < e; i++) {

            String ward1 = scanner.next();
            String ward2 = scanner.next();
            int cost = scanner.nextInt();

            candidate_links.add(
                new Tuple(ward1, ward2, cost)
            );
        }

        List<Pair> scenarios = new ArrayList<>();

        for (int i = 0; i < q; i++) {

            String ward_name = scanner.next();
            int cap = scanner.nextInt();

            scenarios.add(new Pair(ward_name, cap));
        }

        List<Integer> result = user_logic(
            n,
            e,
            q,
            ward_names,
            candidate_links,
            scenarios
        );

        for (int answer : result) {
            System.out.println(answer);
        }

        scanner.close();
    }

    // Represents a candidate road
    static class Tuple {

        String ward1;
        String ward2;
        int cost;

        Tuple(String ward1, String ward2, int cost) {
            this.ward1 = ward1;
            this.ward2 = ward2;
            this.cost = cost;
        }
    }

    // Represents a budget scenario
    static class Pair {

        String ward_name;
        int cap;

        Pair(String ward_name, int cap) {
            this.ward_name = ward_name;
            this.cap = cap;
        }
    }
}

/*
 * ==================== SAMPLE TEST CASE 0 ====================
 *
 * Input:
 * 3 1 2
 * hill
 * port
 * lake
 * hill port 100
 * lake 50
 * port 100
 *
 * Output:
 * 1
 * 2
 *
 * Explanation:
 * Lake has no qualifying road, so only itself is reachable.
 * At budget 100, hill and port are connected, giving 2 wards.
 *
 *
 * ==================== SAMPLE TEST CASE 1 ====================
 *
 * Input:
 * 4 4 3
 * alpha
 * beta
 * gamma
 * delta
 * alpha beta 10
 * beta gamma 20
 * gamma delta 5
 * alpha delta 30
 * alpha 10
 * alpha 20
 * gamma 5
 *
 * Output:
 * 2
 * 4
 * 2
 *
 * Explanation:
 * At budget 10, alpha connects to beta.
 * At budget 20, alpha can reach all four wards.
 * At budget 5, gamma connects only to delta.
 */
