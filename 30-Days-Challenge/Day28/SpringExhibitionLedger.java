/*
Problem: Spring Exhibition Ledger

Given artworks with deadlines and prestige scores, report the
maximum total prestige achievable after each arrival. Each
artwork requires one day and earns prestige only if completed
by its deadline.

Approach:
- Cap deadlines at N because at most N artworks can be restored.
- Use a segment tree to track scheduling feasibility.
- Maintain a min-heap for each deadline to identify the
  lowest-prestige selected artwork.
- When a deadline constraint is violated, remove the
  lowest-prestige artwork needed to restore feasibility.
- Output the total prestige after every arrival.

Time Complexity: O(N log N)
Space Complexity: O(N)

Sample Input 1:
6
2 10
2 20
1 5
3 30
1 40
2 7

Sample Output 1:
10 30 30 60 90 90

Sample Input 2:
5
1000000000 3
3 8
3 6
3 9
2 7

Sample Output 2:
3 11 17 26 27
*/

import java.io.*;
import java.util.*;

public class SpringExhibitionLedger {

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
            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }
            return value;
        }
    }

    static class SlackTree {
        int n;
        int[] min, lazy;

        SlackTree(int n) {
            this.n = n;
            min = new int[4 * n + 5];
            lazy = new int[4 * n + 5];
            build(1, 1, n);
        }

        void build(int node, int l, int r) {
            if (l == r) {
                min[node] = l;
                return;
            }

            int mid = (l + r) / 2;
            build(node * 2, l, mid);
            build(node * 2 + 1, mid + 1, r);
            min[node] = Math.min(min[node * 2], min[node * 2 + 1]);
        }

        void apply(int node, int value) {
            min[node] += value;
            lazy[node] += value;
        }

        void push(int node) {
            if (lazy[node] != 0) {
                apply(node * 2, lazy[node]);
                apply(node * 2 + 1, lazy[node]);
                lazy[node] = 0;
            }
        }

        void add(int ql, int qr, int value) {
            add(1, 1, n, ql, qr, value);
        }

        void add(int node, int l, int r,
                 int ql, int qr, int value) {
            if (ql <= l && r <= qr) {
                apply(node, value);
                return;
            }

            push(node);
            int mid = (l + r) / 2;

            if (ql <= mid)
                add(node * 2, l, mid, ql, qr, value);
            if (qr > mid)
                add(node * 2 + 1, mid + 1, r, ql, qr, value);

            min[node] = Math.min(min[node * 2], min[node * 2 + 1]);
        }

        int firstNegative() {
            if (min[1] >= 0) return -1;
            return firstNegative(1, 1, n);
        }

        int firstNegative(int node, int l, int r) {
            if (l == r) return l;

            push(node);
            int mid = (l + r) / 2;

            if (min[node * 2] < 0)
                return firstNegative(node * 2, l, mid);

            return firstNegative(node * 2 + 1, mid + 1, r);
        }
    }

    static class MinTree {
        int n;
        long[] min;
        static final long INF = Long.MAX_VALUE;

        MinTree(int n) {
            this.n = n;
            min = new long[4 * n + 5];
            Arrays.fill(min, INF);
        }

        void update(int index, long value) {
            update(1, 1, n, index, value);
        }

        void update(int node, int l, int r, int index, long value) {
            if (l == r) {
                min[node] = value;
                return;
            }

            int mid = (l + r) / 2;
            if (index <= mid)
                update(node * 2, l, mid, index, value);
            else
                update(node * 2 + 1, mid + 1, r, index, value);

            min[node] = Math.min(min[node * 2], min[node * 2 + 1]);
        }

        long queryMin(int ql, int qr) {
            return queryMin(1, 1, n, ql, qr);
        }

        long queryMin(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return min[node];

            int mid = (l + r) / 2;
            long answer = INF;

            if (ql <= mid)
                answer = Math.min(answer,
                        queryMin(node * 2, l, mid, ql, qr));
            if (qr > mid)
                answer = Math.min(answer,
                        queryMin(node * 2 + 1, mid + 1, r, ql, qr));

            return answer;
        }

        int findDeadline(int ql, int qr, long value) {
            return findDeadline(1, 1, n, ql, qr, value);
        }

        int findDeadline(int node, int l, int r,
                         int ql, int qr, long value) {
            if (r < ql || l > qr || min[node] > value)
                return -1;

            if (l == r) return l;

            int mid = (l + r) / 2;
            int found = findDeadline(node * 2, l, mid,
                    ql, qr, value);

            if (found != -1) return found;

            return findDeadline(node * 2 + 1, mid + 1, r,
                    ql, qr, value);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = (int) fs.nextLong();

        int[] deadline = new int[n];
        long[] prestige = new long[n];

        for (int i = 0; i < n; i++) {
            deadline[i] = (int) Math.min(fs.nextLong(), n);
            prestige[i] = fs.nextLong();
        }

        SlackTree slack = new SlackTree(n);
        MinTree minTree = new MinTree(n);

        @SuppressWarnings("unchecked")
        PriorityQueue<Long>[] selected = new PriorityQueue[n + 1];

        for (int d = 1; d <= n; d++)
            selected[d] = new PriorityQueue<>();

        long total = 0;
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int d = deadline[i];
            long p = prestige[i];

            selected[d].offer(p);
            minTree.update(d, selected[d].peek());

            slack.add(d, n, -1);
            total += p;

            int violated = slack.firstNegative();

            if (violated != -1) {
                long smallest = minTree.queryMin(1, violated);
                int removeDeadline =
                        minTree.findDeadline(1, violated, smallest);

                selected[removeDeadline].poll();
                total -= smallest;

                long next = selected[removeDeadline].isEmpty()
                        ? Long.MAX_VALUE
                        : selected[removeDeadline].peek();

                minTree.update(removeDeadline, next);
                slack.add(removeDeadline, n, 1);
            }

            if (i > 0) out.append(' ');
            out.append(total);
        }

        System.out.println(out);
    }
}
