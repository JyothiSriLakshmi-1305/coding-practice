/*
 * Problem: Recent Species Sightings
 *
 * Problem Description:
 * Given a chronological sequence of sightings and researcher queries,
 * find the most frequently sighted species within the latest W seconds.
 *
 * A sighting is recent only if:
 * sightingTime > currentTime - W
 *
 * If multiple species have the same frequency, choose the species
 * with the smallest ID.
 *
 * If no recent sightings exist, print "QUIET".
 *
 * Techniques Used:
 * 1. Sliding Window
 * 2. Queue
 * 3. Frequency Array
 * 4. Priority Queue (Max Heap)
 * 5. Lazy Deletion
 * 6. Version Tracking
 *
 * Approach:
 * - Maintain recent sightings in a queue.
 * - Remove expired sightings from the front of the queue.
 * - Update the frequency of the corresponding species.
 * - Maintain a priority queue ordered by highest frequency
 *   and then smallest species ID.
 * - Use version tracking to identify outdated heap entries.
 * - For every query, remove outdated entries from the heap
 *   and return the current highest-frequency species.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N + 200000)
 *
 * Key Learning:
 * Combining a sliding window with a priority queue allows
 * efficient frequency tracking over chronological events.
 * Lazy deletion avoids the need to update existing heap entries.
 */

import java.io.*;
import java.util.*;

public class RecentSpeciesSightings {

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

    static class Sighting {
        long time;
        int id;

        Sighting(long time, int id) {
            this.time = time;
            this.id = id;
        }
    }

    static class Species {
        int id;
        int count;
        int version;

        Species(int id, int count, int version) {
            this.id = id;
            this.count = count;
            this.version = version;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        long w = fs.nextLong();

        int[] freq = new int[200001];
        int[] version = new int[200001];

        Queue<Sighting> window = new ArrayDeque<>();

        PriorityQueue<Species> pq = new PriorityQueue<>((a, b) -> {
            if(a.count != b.count) {
                return Integer.compare(b.count, a.count);
            }

            return Integer.compare(a.id, b.id);
        });

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < n; i++) {
            String type = fs.next();
            long time = fs.nextLong();

            while(!window.isEmpty() && window.peek().time <= time - w) {
                Sighting old = window.poll();

                freq[old.id]--;
                version[old.id]++;

                if(freq[old.id] > 0) {
                    pq.offer(new Species(
                        old.id,
                        freq[old.id],
                        version[old.id]
                    ));
                }
            }

            if(type.equals("S")) {
                int id = fs.nextInt();

                window.offer(new Sighting(time, id));

                freq[id]++;
                version[id]++;

                pq.offer(new Species(
                    id,
                    freq[id],
                    version[id]
                ));
            } else {
                while(!pq.isEmpty()) {
                    Species top = pq.peek();

                    if(top.version != version[top.id] ||
                       top.count != freq[top.id]) {
                        pq.poll();
                    } else {
                        break;
                    }
                }

                if(pq.isEmpty()) {
                    result.append("QUIET\n");
                } else {
                    Species best = pq.peek();

                    result.append(best.id)
                          .append(" ")
                          .append(best.count)
                          .append("\n");
                }
            }
        }

        System.out.print(result);
    }
}
