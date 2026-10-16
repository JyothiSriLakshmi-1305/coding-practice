import java.util.*;

public class DynamicPedestalQueries {

    public static void userLogic(int n, int[] values,
                                 List<Pair<Character, Integer>> operations) {

        TreeSet<Integer> occupied = new TreeSet<>();
        int[] sortedValues = values.clone();
        Arrays.sort(sortedValues);

        FenwickTree fenwick = new FenwickTree(n);
        int[] rankAtPosition = new int[n + 1];
        int[] positionByRank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            occupied.add(i);

            int rank = Arrays.binarySearch(sortedValues, values[i - 1]) + 1;
            rankAtPosition[i] = rank;
            positionByRank[rank] = i;

            fenwick.update(rank, 1);
        }

        for (Pair<Character, Integer> operation : operations) {
            char type = operation.getKey();
            int param = operation.getValue();

            if (type == 'R') {
                if (occupied.remove(param)) {
                    fenwick.update(rankAtPosition[param], -1);
                }
            } else if (type == 'N') {
                Integer left = occupied.lower(param);
                Integer right = occupied.higher(param);

                System.out.println(
                    (left == null ? -1 : left) + " " +
                    (right == null ? -1 : right)
                );
            } else if (type == 'K') {
                int remaining = fenwick.query(n);

                if (param > remaining) {
                    System.out.println("-1 -1");
                } else {
                    int order = remaining - param + 1;
                    int rank = fenwick.findByOrder(order);

                    System.out.println(
                        sortedValues[rank - 1] + " " +
                        positionByRank[rank]
                    );
                }
            }
        }
    }

    static class FenwickTree {
        int[] tree;
        int n;

        FenwickTree(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int delta) {
            while (index <= n) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        int query(int index) {
            int sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }

            return sum;
        }

        int findByOrder(int order) {
            int index = 0;
            int bit = Integer.highestOneBit(n);

            while (bit > 0) {
                int next = index + bit;

                if (next <= n && tree[next] < order) {
                    index = next;
                    order -= tree[next];
                }

                bit >>= 1;
            }

            return index + 1;
        }
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        List<Pair<Character, Integer>> operations = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            char op = scanner.next().charAt(0);
            int param = scanner.nextInt();
            operations.add(new Pair<>(op, param));
        }

        userLogic(n, values, operations);
        scanner.close();
    }
}

/*
Sample Test Case 1

Input:
3
5 15 25
5
R 2
N 1
K 3
R 1
K 1

Output:
-1 3
-1 -1
25 3


Sample Test Case 2

Input:
5
10 50 20 40 30
6
K 2
R 2
N 2
K 3
R 4
K 2

Output:
40 4
1 3
30 5
20 3
*/
