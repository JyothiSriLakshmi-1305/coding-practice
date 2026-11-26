/*
 * Problem: Airport Gate Allocation
 *
 * Description:
 * Assign arriving aircraft to suitable airport gates.
 * If multiple idle gates can accommodate an aircraft,
 * choose the gate with the smallest limit, breaking ties
 * by the smaller gate number.
 *
 * If no suitable gate is available, the aircraft waits.
 * When a gate is released, assign the earliest waiting
 * aircraft that fits that gate.
 *
 * Techniques Used:
 * 1. Greedy Scheduling
 * 2. TreeSet
 * 3. Priority Queue
 * 4. Coordinate Compression
 * 5. Queue
 * 6. Segment Tree
 *
 * Approach:
 * - Store idle gates in a TreeSet ordered by gate limit
 *   and gate number.
 * - Use a PriorityQueue to process gate releases by time.
 * - Maintain waiting aircraft in FIFO queues grouped
 *   by wingspan.
 * - Use coordinate compression to manage wingspans.
 * - Use a Segment Tree to find the earliest eligible
 *   waiting aircraft for a released gate.
 * - Process gate releases before arrivals at equal times.
 *
 * Complexity:
 * Time: O((G + N) log(G + N))
 * Space: O(G + N)
 *
 * Key Learning:
 * Combining greedy scheduling with efficient data
 * structures helps solve complex event-driven allocation
 * problems.
 */

import java.io.*;
import java.util.*;

public class AirportGateAllocation {

    static final int INF = Integer.MAX_VALUE;

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

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while(c <= 32 && c != -1);

            while(c > 32) {
                sb.append((char)c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

    static class Gate {
        long limit;
        int id;

        Gate(long limit, int id) {
            this.limit = limit;
            this.id = id;
        }
    }

    static class Busy {
        long releaseTime;
        int gate;

        Busy(long releaseTime, int gate) {
            this.releaseTime = releaseTime;
            this.gate = gate;
        }
    }

    static class SegmentTree {
        int size;
        int[] tree;

        SegmentTree(int n) {
            size = 1;
            while(size < n) {
                size <<= 1;
            }

            tree = new int[size << 1];
            Arrays.fill(tree, INF);
        }

        void update(int index, int value) {
            index += size;
            tree[index] = value;

            while(index > 1) {
                index >>= 1;
                tree[index] = Math.min(tree[index << 1],
                                       tree[index << 1 | 1]);
            }
        }

        int query(int right) {
            if(right < 0) return INF;

            int left = size;
            right += size + 1;

            int result = INF;

            while(left < right) {
                if((left & 1) == 1) {
                    result = Math.min(result, tree[left]);
                    left++;
                }

                if((right & 1) == 1) {
                    right--;
                    result = Math.min(result, tree[right]);
                }

                left >>= 1;
                right >>= 1;
            }

            return result;
        }
    }

    static int upperBound(long[] arr, long value) {
        int low = 0;
        int high = arr.length;

        while(low < high) {
            int mid = low + (high - low) / 2;

            if(arr[mid] <= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    static void processReleases(
        long until,
        PriorityQueue<Busy> busy,
        TreeSet<Gate> idle,
        ArrayDeque<Integer>[] waiting,
        SegmentTree seg,
        long[] compressed,
        long[] gateLimits,
        int[] compIndex,
        long[] arrival,
        long[] duration,
        int[] assignedGate,
        long[] totalWait,
        long[] lastDeparture
    ) {

        while(!busy.isEmpty() &&
              busy.peek().releaseTime <= until) {

            Busy current = busy.poll();

            int gate = current.gate;
            long time = current.releaseTime;

            int pos = upperBound(compressed, gateLimits[gate]) - 1;
            int aircraft = seg.query(pos);

            if(aircraft == INF) {
                idle.add(new Gate(gateLimits[gate], gate));
            } else {
                int index = compIndex[aircraft];

                ArrayDeque<Integer> queue = waiting[index];
                queue.removeFirst();

                int next = queue.isEmpty() ? INF : queue.peekFirst();
                seg.update(index, next);

                assignedGate[aircraft] = gate;

                totalWait[0] += time - arrival[aircraft];

                long departure = time + duration[aircraft];

                lastDeparture[0] = Math.max(
                    lastDeparture[0], departure
                );

                busy.offer(new Busy(departure, gate));
            }
        }
    }

    public static void main(String[] args) throws IOException {

        FastScanner fs = new FastScanner(System.in);

        int g = fs.nextInt();
        int n = fs.nextInt();

        long[] gateLimits = new long[g + 1];

        TreeSet<Gate> idle = new TreeSet<>((a, b) -> {
            int cmp = Long.compare(a.limit, b.limit);

            if(cmp != 0) return cmp;

            return Integer.compare(a.id, b.id);
        });

        for(int i = 1; i <= g; i++) {
            gateLimits[i] = fs.nextLong();
            idle.add(new Gate(gateLimits[i], i));
        }

        long[] arrival = new long[n + 1];
        long[] wingspan = new long[n + 1];
        long[] duration = new long[n + 1];

        long[] compressed = new long[n];

        for(int i = 1; i <= n; i++) {
            arrival[i] = fs.nextLong();
            wingspan[i] = fs.nextLong();
            duration[i] = fs.nextLong();

            compressed[i - 1] = wingspan[i];
        }

        Arrays.sort(compressed);

        int unique = 0;

        for(int i = 0; i < n; i++) {
            if(unique == 0 || compressed[i] != compressed[unique - 1]) {
                compressed[unique++] = compressed[i];
            }
        }

        compressed = Arrays.copyOf(compressed, unique);

        int[] compIndex = new int[n + 1];

        @SuppressWarnings("unchecked")
        ArrayDeque<Integer>[] waiting = new ArrayDeque[unique];

        for(int i = 0; i < unique; i++) {
            waiting[i] = new ArrayDeque<>();
        }

        for(int i = 1; i <= n; i++) {
            compIndex[i] = Arrays.binarySearch(
                compressed, wingspan[i]
            );
        }

        SegmentTree seg = new SegmentTree(unique);

        PriorityQueue<Busy> busy = new PriorityQueue<>((a, b) -> {
            int cmp = Long.compare(a.releaseTime, b.releaseTime);

            if(cmp != 0) return cmp;

            return Integer.compare(a.gate, b.gate);
        });

        int[] assignedGate = new int[n + 1];

        long[] totalWait = {0};
        long[] lastDeparture = {0};

        for(int i = 1; i <= n; i++) {

            processReleases(
                arrival[i],
                busy,
                idle,
                waiting,
                seg,
                compressed,
                gateLimits,
                compIndex,
                arrival,
                duration,
                assignedGate,
                totalWait,
                lastDeparture
            );

            Gate selected = idle.ceiling(
                new Gate(wingspan[i], 0)
            );

            if(selected != null) {
                idle.remove(selected);

                assignedGate[i] = selected.id;

                long departure = arrival[i] + duration[i];

                lastDeparture[0] = Math.max(
                    lastDeparture[0], departure
                );

                busy.offer(new Busy(departure, selected.id));

            } else {
                int index = compIndex[i];

                waiting[index].offerLast(i);

                seg.update(index, waiting[index].peekFirst());
            }
        }

        processReleases(
            Long.MAX_VALUE,
            busy,
            idle,
            waiting,
            seg,
            compressed,
            gateLimits,
            compIndex,
            arrival,
            duration,
            assignedGate,
            totalWait,
            lastDeparture
        );

        StringBuilder result = new StringBuilder();

        result.append(totalWait[0])
              .append(" ")
              .append(lastDeparture[0])
              .append("\n");

        for(int i = 1; i <= n; i++) {
            if(i > 1) result.append(" ");

            result.append(assignedGate[i]);
        }

        System.out.println(result);
    }
}
