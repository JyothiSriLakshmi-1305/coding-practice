
/*
 * Problem: Meridian Deep Sea Research Station
 *
 * Problem Description:
 * Given modules with battery capacities and cables connecting them,
 * each event removes one cable from the network.
 *
 * After every removal, find:
 * 1. The number of connected components.
 * 2. The K-th largest component capacity.
 *
 * Techniques Used:
 * 1. Reverse Processing
 * 2. Disjoint Set Union (DSU)
 * 3. Path Compression
 * 4. Union by Size
 * 5. Coordinate Compression
 * 6. Fenwick Tree (Binary Indexed Tree)
 *
 * Approach:
 * - Mark all cables that will be removed.
 * - Initially build DSU using cables that are never removed.
 * - Process events in reverse order and add cables back.
 * - Maintain component capacities using a Fenwick Tree.
 * - Use coordinate compression to handle large capacity values.
 * - Find the K-th largest capacity using Fenwick Tree binary lifting.
 * - Store answers and print them in the original event order.
 *
 * Time Complexity: O((M + C + S) log(M + S))
 * Space Complexity: O(M + C + S)
 */

import java.io.*;
import java.util.*;

public class MeridianDeepSeaResearchStation {

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if(ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if(len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;

            do {
                c = read();
            } while(c <= ' ' && c != -1);

            int num = 0;

            while(c > ' ') {
                num = num * 10 + c - '0';
                c = read();
            }

            return num;
        }

        long nextLong() throws IOException {
            int c;

            do {
                c = read();
            } while(c <= ' ' && c != -1);

            long num = 0;

            while(c > ' ') {
                num = num * 10 + c - '0';
                c = read();
            }

            return num;
        }
    }

    static class DSU {
        int[] parent;
        int[] size;
        long[] sum;

        DSU(long[] capacity) {
            int n = capacity.length;

            parent = new int[n];
            size = new int[n];
            sum = new long[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                sum[i] = capacity[i];
            }
        }

        int find(int x) {
            while(parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }

            return x;
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);

            if(a == b) return;

            if(size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            parent[b] = a;
            size[a] += size[b];
            sum[a] += sum[b];
        }
    }

    static class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            index++;

            while(index <= n) {
                tree[index] += value;
                index += index & -index;
            }
        }

        int kth(int k) {
            int index = 0;
            int bit = Integer.highestOneBit(n);

            while(bit > 0) {
                int next = index + bit;

                if(next <= n && tree[next] < k) {
                    index = next;
                    k -= tree[next];
                }

                bit >>= 1;
            }

            return index;
        }
    }

    static int M, C, S;

    static int[] U, V;
    static int[] eventCable, eventRank;
    static boolean[] removed;
    static long[] capacity;

    static DSU buildDSU() {
        DSU dsu = new DSU(capacity);

        for(int i = 1; i <= C; i++) {
            if(!removed[i]) {
                dsu.union(U[i], V[i]);
            }
        }

        return dsu;
    }

    static int lowerBound(long[] arr, int n, long value) {
        int left = 0;
        int right = n;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner(System.in);

        M = fs.nextInt();
        C = fs.nextInt();
        S = fs.nextInt();

        capacity = new long[M];

        for(int i = 0; i < M; i++) {
            capacity[i] = fs.nextLong();
        }

        U = new int[C + 1];
        V = new int[C + 1];

        for(int i = 1; i <= C; i++) {
            U[i] = fs.nextInt() - 1;
            V[i] = fs.nextInt() - 1;
        }

        eventCable = new int[S];
        eventRank = new int[S];
        removed = new boolean[C + 1];

        for(int i = 0; i < S; i++) {
            eventCable[i] = fs.nextInt();
            eventRank[i] = fs.nextInt();
            removed[eventCable[i]] = true;
        }

        DSU dsu = buildDSU();

        long[] values = new long[M + S + 5];
        int valueCount = 0;

        for(int i = 0; i < M; i++) {
            if(dsu.find(i) == i) {
                values[valueCount++] = dsu.sum[i];
            }
        }

        for(int i = S - 1; i >= 0; i--) {
            int cable = eventCable[i];

            int a = dsu.find(U[cable]);
            int b = dsu.find(V[cable]);

            if(a != b) {
                dsu.union(a, b);
                values[valueCount++] = dsu.sum[dsu.find(a)];
            }
        }

        Arrays.sort(values, 0, valueCount);

        int uniqueCount = 0;

        for(int i = 0; i < valueCount; i++) {
            if(uniqueCount == 0 ||
               values[i] != values[uniqueCount - 1]) {

                values[uniqueCount++] = values[i];
            }
        }

        dsu = buildDSU();

        Fenwick bit = new Fenwick(uniqueCount);

        int components = 0;

        for(int i = 0; i < M; i++) {
            if(dsu.find(i) == i) {
                int index = lowerBound(
                    values, uniqueCount, dsu.sum[i]
                );

                bit.update(index, 1);
                components++;
            }
        }

        int[] answerCount = new int[S];
        long[] answerCapacity = new long[S];

        for(int i = S - 1; i >= 0; i--) {

            int k = eventRank[i];

            answerCount[i] = components;

            if(k <= components) {
                int target = components - k + 1;
                int index = bit.kth(target);

                answerCapacity[i] = values[index];
            } else {
                answerCapacity[i] = 0;
            }

            int cable = eventCable[i];

            int a = dsu.find(U[cable]);
            int b = dsu.find(V[cable]);

            if(a != b) {

                int indexA = lowerBound(
                    values, uniqueCount, dsu.sum[a]
                );

                int indexB = lowerBound(
                    values, uniqueCount, dsu.sum[b]
                );

                bit.update(indexA, -1);
                bit.update(indexB, -1);

                dsu.union(a, b);

                int root = dsu.find(a);

                int mergedIndex = lowerBound(
                    values, uniqueCount, dsu.sum[root]
                );

                bit.update(mergedIndex, 1);

                components--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < S; i++) {
            sb.append(answerCount[i])
              .append(' ')
              .append(answerCapacity[i])
              .append('\n');
        }

        System.out.print(sb);
    }
}
