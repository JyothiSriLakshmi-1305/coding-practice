import java.io.*;
import java.util.*;

public class CobaltPottery {

    static final long INF = Long.MAX_VALUE / 4;

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while(st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    static class SegmentTree {
        int size;
        long[] tree;
        long[] lazy;
        int[] loads;

        SegmentTree(int n) {
            size = n;
            tree = new long[4 * n];
            lazy = new long[4 * n];
            loads = new int[4 * n];

            Arrays.fill(tree, INF);
            Arrays.fill(loads, Integer.MAX_VALUE);
        }

        void apply(int node, long value) {
            if(tree[node] < INF / 2) {
                tree[node] += value;
            }
            lazy[node] += value;
        }

        void push(int node) {
            if(lazy[node] != 0) {
                apply(node * 2, lazy[node]);
                apply(node * 2 + 1, lazy[node]);
                lazy[node] = 0;
            }
        }

        boolean better(int a, int b) {
            if(tree[a] != tree[b]) {
                return tree[a] < tree[b];
            }
            return loads[a] < loads[b];
        }

        void pull(int node) {
            int left = node * 2;
            int right = left + 1;

            if(better(left, right)) {
                tree[node] = tree[left];
                loads[node] = loads[left];
            } else {
                tree[node] = tree[right];
                loads[node] = loads[right];
            }
        }

        void update(int node, int start, int end, int l, int r, long value) {
            if(r < start || end < l) {
                return;
            }

            if(l <= start && end <= r) {
                apply(node, value);
                return;
            }

            push(node);

            int mid = (start + end) / 2;

            update(node * 2, start, mid, l, r, value);
            update(node * 2 + 1, mid + 1, end, l, r, value);

            pull(node);
        }

        void rangeAdd(int l, int r, long value) {
            if(l <= r) {
                update(1, 0, size - 1, l, r, value);
            }
        }

        void setPoint(int node, int start, int end, int index,
                      long value, int count) {
            if(start == end) {
                tree[node] = value;
                loads[node] = count;
                lazy[node] = 0;
                return;
            }

            push(node);

            int mid = (start + end) / 2;

            if(index <= mid) {
                setPoint(node * 2, start, mid, index, value, count);
            } else {
                setPoint(node * 2 + 1, mid + 1, end, index, value, count);
            }

            pull(node);
        }

        void setPoint(int index, long value, int count) {
            setPoint(1, 0, size - 1, index, value, count);
        }

        int query(int node, int start, int end, int l, int r) {
            if(r < start || end < l) {
                return -1;
            }

            if(l <= start && end <= r) {
                return node;
            }

            push(node);

            int mid = (start + end) / 2;

            int left = query(node * 2, start, mid, l, r);
            int right = query(node * 2 + 1, mid + 1, end, l, r);

            if(left == -1) {
                return right;
            }

            if(right == -1) {
                return left;
            }

            return better(left, right) ? left : right;
        }

        int query(int l, int r) {
            if(l > r) {
                return -1;
            }

            return query(1, 0, size - 1, l, r);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int A = fs.nextInt();
        int B = fs.nextInt();

        int[] a = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            a[i] = fs.nextInt();
        }

        long[] dp = new long[n + 1];
        int[] count = new int[n + 1];

        Arrays.fill(dp, INF);
        Arrays.fill(count, Integer.MAX_VALUE);

        dp[0] = 0;
        count[0] = 0;

        SegmentTree seg = new SegmentTree(n + 1);

        int[] maxStack = new int[n + 1];
        int[] minStack = new int[n + 1];

        int maxTop = -1;
        int minTop = -1;

        for(int i = 1; i <= n; i++) {
            int x = a[i];

            while(maxTop >= 0 && a[maxStack[maxTop]] <= x) {
                int k = maxStack[maxTop--];
                int left = maxTop >= 0 ? maxStack[maxTop] : 0;

                seg.rangeAdd(left, k - 1, (long)x - a[k]);
            }

            maxStack[++maxTop] = i;

            while(minTop >= 0 && a[minStack[minTop]] >= x) {
                int k = minStack[minTop--];
                int left = minTop >= 0 ? minStack[minTop] : 0;

                seg.rangeAdd(left, k - 1, (long)a[k] - x);
            }

            minStack[++minTop] = i;

            seg.setPoint(i - 1, dp[i - 1], count[i - 1]);

            int l = Math.max(0, i - B);
            int r = i - A;

            int best = seg.query(l, r);

            if(best != -1 && seg.tree[best] < INF / 2) {
                dp[i] = seg.tree[best];
                count[i] = seg.loads[best] + 1;
            }
        }

        if(dp[n] == INF) {
            System.out.println("Cobalt plan: impossible");
        } else {
            System.out.println("Cobalt plan: waste " + dp[n] +
                               " with " + count[n] + " loads");
        }
    }
}
