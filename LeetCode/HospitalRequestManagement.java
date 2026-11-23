
/*
 * Problem: Hospital Request Management
 *
 * Description:
 * Manage hospital requests across multiple wards.
 * Each request has a unique ID and urgency.
 *
 * Operations:
 * NEW      - Add a new request.
 * CHANGE   - Update the urgency of a pending request.
 * WITHDRAW - Cancel a pending request.
 * SERVE    - Serve the highest-priority request in a ward.
 *
 * Priority Rules:
 * 1. Higher urgency gets higher priority.
 * 2. If urgency is equal, smaller request ID gets priority.
 *
 * Techniques Used:
 * - PriorityQueue
 * - HashMap
 * - Custom Comparator
 * - Lazy Deletion
 *
 * Approach:
 * - Maintain a separate PriorityQueue for each ward.
 * - Use a HashMap to track the latest valid request.
 * - On CHANGE, insert an updated entry with a new version.
 * - On WITHDRAW, remove the request from the HashMap.
 * - During SERVE, discard outdated queue entries using version numbers.
 * - Serve the valid request at the top of the priority queue.
 *
 * Time Complexity: O(q log q) overall
 * Space Complexity: O(q)
 *
 * Key Learning:
 * Lazy deletion allows efficient priority updates and cancellations
 * without directly removing arbitrary elements from a PriorityQueue.
 */

import java.io.*;
import java.util.*;

public class HospitalRequestManagement {

    static class Request {
        int id, ward, urgency, version;

        Request(int id, int ward, int urgency, int version) {
            this.id = id;
            this.ward = ward;
            this.urgency = urgency;
            this.version = version;
        }
    }

    static class Entry {
        int id, urgency, version;

        Entry(int id, int urgency, int version) {
            this.id = id;
            this.urgency = urgency;
            this.version = version;
        }
    }

    static PriorityQueue<Entry> createQueue() {
        return new PriorityQueue<>((a, b) -> {
            if(a.urgency != b.urgency) {
                return Integer.compare(b.urgency, a.urgency);
            }
            return Integer.compare(a.id, b.id);
        });
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine().trim());

        HashMap<Integer, Request> requests = new HashMap<>();
        HashMap<Integer, PriorityQueue<Entry>> queues = new HashMap<>();

        int[] versions = new int[200001];

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();

            if(operation.equals("NEW")) {
                int id = Integer.parseInt(st.nextToken());
                int ward = Integer.parseInt(st.nextToken());
                int urgency = Integer.parseInt(st.nextToken());

                int version = ++versions[id];

                requests.put(id, new Request(id, ward, urgency, version));

                queues.putIfAbsent(ward, createQueue());
                queues.get(ward).offer(new Entry(id, urgency, version));
            }

            else if(operation.equals("CHANGE")) {
                int id = Integer.parseInt(st.nextToken());
                int urgency = Integer.parseInt(st.nextToken());

                Request old = requests.get(id);
                int version = ++versions[id];

                requests.put(id, new Request(id, old.ward, urgency, version));

                queues.get(old.ward).offer(new Entry(id, urgency, version));
            }

            else if(operation.equals("WITHDRAW")) {
                int id = Integer.parseInt(st.nextToken());

                requests.remove(id);
                versions[id]++;
            }

            else if(operation.equals("SERVE")) {
                int ward = Integer.parseInt(st.nextToken());

                PriorityQueue<Entry> pq = queues.get(ward);

                if(pq == null) {
                    sb.append("NONE\n");
                    continue;
                }

                while(!pq.isEmpty()) {
                    Entry top = pq.peek();
                    Request current = requests.get(top.id);

                    if(current == null || current.version != top.version) {
                        pq.poll();
                    } else {
                        break;
                    }
                }

                if(pq.isEmpty()) {
                    sb.append("NONE\n");
                } else {
                    Entry served = pq.poll();

                    sb.append(served.id).append(" ")
                      .append(served.urgency).append("\n");

                    requests.remove(served.id);
                    versions[served.id]++;
                }
            }
        }

        System.out.print(sb);
    }
}
