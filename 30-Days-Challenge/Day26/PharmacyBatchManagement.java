/*
Problem: Pharmacy Batch Management

Track vaccine batches, serve ward requests using the
earliest-expiring batches first, process recalls, and
calculate expired and recalled doses.

Approach:
- PriorityQueue: Process batches by expiry day.
- HashMap: Find batches quickly during recalls.
- Remove expired batches before each log entry.
- Serve requests greedily from earliest-expiring batches.
- Count remaining doses as expired or recalled.

Time Complexity: O(N log N)
Space Complexity: O(N)

Sample Input 1:
6 15
1 R 31 6 3
1 R 32 2 5
3 D 4
5 R 33 14 8
8 D 10
9 C 99

Sample Output 1:
3
8
EXPIRED 5 RECALLED 0

Sample Input 2:
9 20
1 R 101 5 10
1 R 102 9 6
2 D 8
3 R 103 4 5
4 D 7
5 R 104 7 4
6 C 102
6 D 3
8 D 20

Sample Output 2:
8
7
3
0
EXPIRED 1 RECALLED 6
*/

import java.io.*;
import java.util.*;

public class PharmacyBatchManagement {

    static class Batch {
        long id, expiry, doses;
        boolean active = true;

        Batch(long id, long expiry, long doses) {
            this.id = id;
            this.expiry = expiry;
            this.doses = doses;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long Z = Long.parseLong(st.nextToken());

        PriorityQueue<Batch> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(a.expiry, b.expiry));

        Map<Long, Batch> batches = new HashMap<>();
        StringBuilder output = new StringBuilder();

        long expired = 0;
        long recalled = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long day = Long.parseLong(st.nextToken());
            String type = st.nextToken();

            while (!pq.isEmpty() && pq.peek().expiry < day) {
                Batch b = pq.poll();

                if (b.active) {
                    expired += b.doses;
                    b.doses = 0;
                    b.active = false;
                }
            }

            if (type.equals("R")) {
                long id = Long.parseLong(st.nextToken());
                long expiry = Long.parseLong(st.nextToken());
                long doses = Long.parseLong(st.nextToken());

                Batch b = new Batch(id, expiry, doses);
                batches.put(id, b);
                pq.offer(b);

            } else if (type.equals("D")) {
                long requested = Long.parseLong(st.nextToken());
                long supplied = 0;

                while (requested > 0 && !pq.isEmpty()) {
                    Batch b = pq.peek();

                    if (!b.active || b.doses == 0) {
                        pq.poll();
                        continue;
                    }

                    long used = Math.min(requested, b.doses);

                    supplied += used;
                    requested -= used;
                    b.doses -= used;

                    if (b.doses == 0) {
                        b.active = false;
                        pq.poll();
                    }
                }

                output.append(supplied).append('\n');

            } else if (type.equals("C")) {
                long id = Long.parseLong(st.nextToken());
                Batch b = batches.get(id);

                if (b != null && b.active) {
                    recalled += b.doses;
                    b.doses = 0;
                    b.active = false;
                }
            }
        }

        while (!pq.isEmpty()) {
            Batch b = pq.poll();

            if (b.active && b.expiry < Z) {
                expired += b.doses;
                b.doses = 0;
                b.active = false;
            }
        }

        output.append("EXPIRED ")
              .append(expired)
              .append(" RECALLED ")
              .append(recalled);

        System.out.print(output);
    }
}
