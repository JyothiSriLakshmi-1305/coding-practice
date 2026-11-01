import java.io.*;
import java.util.*;

public class Main {

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            long value = 0;
            boolean negative = false;

            if (c == '-') {
                negative = true;
                c = read();
            }

            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }

            return negative ? -value : value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    static class Edge {
        int u, v;
        long h;

        Edge(int u, int v, long h) {
            this.u = u;
            this.v = v;
            this.h = h;
        }
    }

    static class Event {
        int type, x;
        long value;

        Event(int type, int x, long value) {
            this.type = type;
            this.x = x;
            this.value = value;
        }
    }

    static class FenwickTree {
        long[] bit;
        int n;

        FenwickTree(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void add(int index, long delta) {
            for (int i = index; i <= n; i += i & -i) {
                bit[i] += delta;
            }
        }

        long prefixSum(int index) {
            long sum = 0;
            for (int i = index; i > 0; i -= i & -i) {
                sum += bit[i];
            }
            return sum;
        }

        long rangeSum(int left, int right) {
            return prefixSum(right) - prefixSum(left - 1);
        }
    }

    static int[] dsu, dsuSize, componentTree;
    static int find(int x) {
        while (dsu[x] != x) {
            dsu[x] = dsu[dsu[x]];
            x = dsu[x];
        }
        return x;
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return a;

        if (dsuSize[a] < dsuSize[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        dsu[b] = a;
        dsuSize[a] += dsuSize[b];
        return a;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();
        int q = fs.nextInt();

        long[] funds = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            funds[i] = fs.nextLong();
        }

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            long h = fs.nextLong();
            edges[i] = new Edge(u, v, h);
        }

        Event[] events = new Event[q];
        for (int i = 0; i < q; i++) {
            int type = fs.nextInt();
            int x = fs.nextInt();
            long value = fs.nextLong();
            events[i] = new Event(type, x, value);
        }

        Arrays.sort(edges, Comparator.comparingLong(e -> e.h));

        int maxNodes = 2 * n;
        int[] left = new int[maxNodes];
        int[] right = new int[maxNodes];
        int[] parent = new int[maxNodes];
        long[] weight = new long[maxNodes];

        Arrays.fill(parent, -1);

        dsu = new int[n + 1];
        dsuSize = new int[n + 1];
        componentTree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dsu[i] = i;
            dsuSize[i] = 1;
            componentTree[i] = i;
        }

        int totalNodes = n;

        // Build the Kruskal Reconstruction Forest.
        for (Edge e : edges) {
            int ru = find(e.u);
            int rv = find(e.v);

            if (ru == rv) continue;

            int node = ++totalNodes;
            weight[node] = e.h;
            left[node] = componentTree[ru];
            right[node] = componentTree[rv];

            parent[left[node]] = node;
            parent[right[node]] = node;

            int newRoot = union(ru, rv);
            componentTree[newRoot] = node;
        }

        int log = 1;
        while ((1 << log) <= totalNodes) log++;

        int[][] up = new int[log][totalNodes + 1];

        for (int i = 1; i <= totalNodes; i++) {
            up[0][i] = parent[i] == -1 ? 0 : parent[i];
        }

        for (int k = 1; k < log; k++) {
            for (int i = 1; i <= totalNodes; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        // Assign a DFS position to every original district.
        int[] position = new int[n + 1];
        int[] stack = new int[totalNodes + 1];
        int top;
        int timer = 0;

        for (int i = 1; i <= totalNodes; i++) {
            if (parent[i] != -1) continue;

            top = 0;
            stack[top++] = i;

            while (top > 0) {
                int node = stack[--top];

                if (node <= n) {
                    position[node] = ++timer;
                } else {
                    stack[top++] = right[node];
                    stack[top++] = left[node];
                }
            }
        }

        // Determine each reconstruction node's leaf interval and size.
        int[] lo = new int[totalNodes + 1];
        int[] hi = new int[totalNodes + 1];
        int[] count = new int[totalNodes + 1];

        for (int i = 1; i <= n; i++) {
            lo[i] = hi[i] = position[i];
            count[i] = 1;
        }

        for (int i = n + 1; i <= totalNodes; i++) {
            lo[i] = Math.min(lo[left[i]], lo[right[i]]);
            hi[i] = Math.max(hi[left[i]], hi[right[i]]);
            count[i] = count[left[i]] + count[right[i]];
        }

        FenwickTree fenwick = new FenwickTree(n);

        for (int i = 1; i <= n; i++) {
            fenwick.add(position[i], funds[i]);
        }

        StringBuilder output = new StringBuilder();

        for (Event event : events) {
            if (event.type == 1) {
                int x = event.x;
                long newFund = event.value;

                fenwick.add(position[x], newFund - funds[x]);
                funds[x] = newFund;

            } else {
                int x = event.x;
                long level = event.value;

                // Find the highest ancestor whose threshold <= level.
                int node = x;

                for (int k = log - 1; k >= 0; k--) {
                    int ancestor = up[k][node];

                    if (ancestor != 0 && weight[ancestor] <= level) {
                        node = ancestor;
                    }
                }

                long totalFund = fenwick.rangeSum(lo[node], hi[node]);
                int reachableDistricts = count[node];

                output.append(totalFund)
                      .append(' ')
                      .append(reachableDistricts)
                      .append('\n');
            }
        }

        System.out.print(output);
    }
}
