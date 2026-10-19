import java.util.*;

/*
 * Day 20: Dynamic Substation Network
 *
 * Problem:
 * Manage substations connected by transmission lines.
 * Each substation has a fixed capacity and starts OFF.
 *
 * Operations:
 * LINK a b - Merge two connected components.
 * ON s     - Activate substation s.
 * OFF s    - Deactivate substation s.
 * QUERY s  - Find the maximum active capacity in s's component.
 *
 * Approach:
 * 1. DSU (Union-Find) tracks connected components.
 * 2. Each component maintains a TreeSet of active substations.
 * 3. TreeSet orders substations by capacity, then ID.
 * 4. Small-to-large merging combines active sets efficiently.
 *
 * Time Complexity:
 * Approximately O((N + M) log^2 N) with small-to-large merging.
 *
 * Space Complexity: O(N + M)
 *
 * Sample Test Case 1
 *
 * Input:
 * 5
 * 10 40 20 50 30
 * 8
 * ON 1
 * ON 2
 * QUERY 1
 * LINK 1 2
 * QUERY 1
 * OFF 2
 * QUERY 2
 * QUERY 3
 *
 * Output:
 * 10 1
 * 40 2
 * 10 1
 * -1 -1
 *
 * Sample Test Case 2
 *
 * Input:
 * 4
 * 5 8 3 12
 * 9
 * ON 3
 * ON 4
 * LINK 3 4
 * QUERY 4
 * OFF 4
 * QUERY 3
 * ON 1
 * LINK 1 3
 * QUERY 1
 *
 * Output:
 * 12 4
 * 3 3
 * 5 1
 */

public class DynamicSubstationNetwork {

    static class DSU {
        int[] parent;
        int[] size;
        int[] capacities;
        TreeSet<Integer>[] activeSets;

        @SuppressWarnings("unchecked")
        DSU(int n, int[] capacities) {
            this.capacities = capacities;
            parent = new int[n + 1];
            size = new int[n + 1];
            activeSets = new TreeSet[n + 1];

            Comparator<Integer> comparator = (a, b) -> {
                if (capacities[a - 1] != capacities[b - 1]) {
                    return Integer.compare(
                        capacities[a - 1], capacities[b - 1]
                    );
                }
                return Integer.compare(a, b);
            };

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
                activeSets[i] = new TreeSet<>(comparator);
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void activate(int x) {
            activeSets[find(x)].add(x);
        }

        void deactivate(int x) {
            activeSets[find(x)].remove(x);
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            // Merge the component with fewer active stations
            // into the component with more active stations.
            if (activeSets[rootA].size() < activeSets[rootB].size()) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];

            activeSets[rootA].addAll(activeSets[rootB]);
            activeSets[rootB].clear();
        }

        void query(int x) {
            TreeSet<Integer> set = activeSets[find(x)];

            if (set.isEmpty()) {
                System.out.println("-1 -1");
            } else {
                int station = set.last();
                System.out.println(
                    capacities[station - 1] + " " + station
                );
            }
        }
    }

    public static void manageSubstations(
            int n, int[] capacities, int m, List<String> logEntries) {

        DSU dsu = new DSU(n, capacities);

        for (String entry : logEntries) {
            String[] parts = entry.split("\\s+");
            String command = parts[0];

            switch (command) {
                case "LINK":
                    dsu.union(
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2])
                    );
                    break;

                case "ON":
                    dsu.activate(Integer.parseInt(parts[1]));
                    break;

                case "OFF":
                    dsu.deactivate(Integer.parseInt(parts[1]));
                    break;

                case "QUERY":
                    dsu.query(Integer.parseInt(parts[1]));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] capacities = new int[n];

        for (int i = 0; i < n; i++) {
            capacities[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        List<String> logEntries = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String command = scanner.next();

            if (command.equals("LINK")) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                logEntries.add(command + " " + a + " " + b);
            } else {
                int station = scanner.nextInt();
                logEntries.add(command + " " + station);
            }
        }

        manageSubstations(n, capacities, m, logEntries);
        scanner.close();
    }
}
