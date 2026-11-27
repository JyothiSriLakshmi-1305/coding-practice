/*
 * Problem: Plasma Unit Management
 *
 * Description:
 * Manage plasma deliveries, withdrawals and hospital requests.
 * For each request, select the usable plasma unit with the
 * earliest expiry day. If expiry days are equal, select the
 * unit with the smallest identifier.
 *
 * Techniques Used:
 * 1. Priority Queue
 * 2. HashMap
 * 3. Lazy Deletion
 * 4. Greedy Selection
 *
 * Approach:
 * - Maintain a separate PriorityQueue for every blood group.
 * - Sort units by expiry day, then by identifier.
 * - Use a HashMap to track whether each unit is active.
 * - On withdrawal, mark the unit inactive.
 * - On a request, remove expired or inactive units from
 *   the top of the corresponding priority queue.
 * - Select the first valid unit and mark it inactive.
 * - Print NONE if no usable unit is available.
 *
 * Expiry Rule:
 * A unit is usable when expiry >= current day.
 *
 * Complexity:
 * Time: O(Q log Q)
 * Space: O(Q + G)
 *
 * Key Learning:
 * Lazy deletion avoids expensive arbitrary removal from
 * priority queues and makes event processing efficient.
 */

import java.io.*;
import java.util.*;

public class PlasmaUnitManagement {

    static class Unit {
        long id;
        long expiry;

        Unit(long id, long expiry) {
            this.id = id;
            this.expiry = expiry;
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(
                new InputStreamReader(System.in)
            );
        }

        String next() throws IOException {
            while(st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) throws IOException {

        FastScanner fs = new FastScanner();

        int q = fs.nextInt();
        int G = fs.nextInt();

        List<PriorityQueue<Unit>> groups = new ArrayList<>();

        for(int i = 0; i <= G; i++) {

            groups.add(new PriorityQueue<>((a, b) -> {

                int cmp = Long.compare(a.expiry, b.expiry);

                if(cmp != 0) {
                    return cmp;
                }

                return Long.compare(a.id, b.id);
            }));
        }

        HashMap<Long, Boolean> active = new HashMap<>();

        StringBuilder output = new StringBuilder();

        for(int i = 0; i < q; i++) {

            long day = fs.nextLong();
            String type = fs.next();

            if(type.equals("D")) {

                long id = fs.nextLong();
                int group = fs.nextInt();
                long expiry = fs.nextLong();

                groups.get(group).offer(
                    new Unit(id, expiry)
                );

                active.put(id, true);

            } else if(type.equals("C")) {

                long id = fs.nextLong();

                if(active.getOrDefault(id, false)) {
                    active.put(id, false);
                }

            } else if(type.equals("R")) {

                int group = fs.nextInt();

                PriorityQueue<Unit> pq = groups.get(group);

                while(!pq.isEmpty()) {

                    Unit unit = pq.peek();

                    if(!active.getOrDefault(unit.id, false)
                            || unit.expiry < day) {

                        pq.poll();
                        active.put(unit.id, false);

                    } else {
                        break;
                    }
                }

                if(pq.isEmpty()) {

                    output.append("NONE\n");

                } else {

                    Unit selected = pq.poll();

                    active.put(selected.id, false);

                    output.append(selected.id).append('\n');
                }
            }
        }

        System.out.print(output);
    }
}
