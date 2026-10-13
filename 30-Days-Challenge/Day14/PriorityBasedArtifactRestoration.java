
import java.util.*;

/*
 * Day 14: Priority-Based Artifact Restoration
 *
 * Aim:
 * Process artifact arrivals, withdrawals, and restoration
 * requests according to urgency and catalogue number.
 *
 * Problem Statement:
 * Each artifact has a unique ID and an urgency rating.
 *
 * ARRIVE id urgency:
 * Add an artifact to the waiting queue.
 *
 * WITHDRAW id:
 * Remove an artifact from consideration before restoration.
 *
 * RESTORE:
 * Restore the artifact with the highest urgency.
 * If urgency values are tied, select the smallest ID.
 * If no artifact is waiting, print "IDLE".
 *
 * Approach:
 * 1. Use a PriorityQueue ordered by urgency descending,
 *    then ID ascending.
 * 2. Use a HashMap to track active artifacts.
 * 3. Use lazy deletion to skip withdrawn artifacts.
 * 4. Mark restored artifacts inactive to prevent reuse.
 *
 * Time Complexity: O(M log M) overall
 * Space Complexity: O(M)
 */

public class PriorityBasedArtifactRestoration {

    static class Artifact {
        int id;
        int urgency;

        Artifact(int id, int urgency) {
            this.id = id;
            this.urgency = urgency;
        }
    }

    public static List<String> processEvents(
            int m, List<String> events) {

        List<String> results = new ArrayList<>();

        // Highest urgency first; smallest ID breaks ties
        PriorityQueue<Artifact> pq = new PriorityQueue<>(
            (a, b) -> {
                int comparison = Integer.compare(
                    b.urgency,
                    a.urgency
                );

                if (comparison != 0) {
                    return comparison;
                }

                return Integer.compare(a.id, b.id);
            }
        );

        // True means the artifact is still waiting
        Map<Integer, Boolean> active = new HashMap<>();

        for (String event : events) {

            String[] parts = event.trim().split("\\s+");
            String type = parts[0];

            if (type.equals("ARRIVE")) {

                int id = Integer.parseInt(parts[1]);
                int urgency = Integer.parseInt(parts[2]);

                pq.offer(new Artifact(id, urgency));
                active.put(id, true);

            } else if (type.equals("WITHDRAW")) {

                int id = Integer.parseInt(parts[1]);

                // Mark the artifact inactive
                active.put(id, false);

            } else if (type.equals("RESTORE")) {

                // Remove inactive artifacts from the queue's front
                while (!pq.isEmpty()) {

                    Artifact top = pq.peek();

                    if (active.getOrDefault(top.id, false)) {
                        break;
                    }

                    pq.poll();
                }

                if (pq.isEmpty()) {

                    results.add("IDLE");

                } else {

                    Artifact restored = pq.poll();

                    // A restored artifact is no longer waiting
                    active.put(restored.id, false);

                    results.add(String.valueOf(restored.id));
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        scanner.nextLine();

        List<String> events = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            events.add(scanner.nextLine());
        }

        List<String> results = processEvents(m, events);

        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}

/*
 * ==================== SAMPLE TEST CASE 0 ====================
 *
 * Input:
 * 6
 * ARRIVE 101 50
 * ARRIVE 102 80
 * RESTORE
 * ARRIVE 103 90
 * WITHDRAW 101
 * RESTORE
 *
 * Output:
 * 102
 * 103
 *
 * Explanation:
 * Artifact 102 has higher urgency than artifact 101, so it is
 * restored first. Artifact 103 then arrives with urgency 90.
 * Artifact 101 is withdrawn, and artifact 103 is restored next.
 *
 *
 * ==================== SAMPLE TEST CASE 1 ====================
 *
 * Input:
 * 10
 * ARRIVE 500 30
 * ARRIVE 200 50
 * ARRIVE 700 50
 * RESTORE
 * WITHDRAW 500
 * RESTORE
 * ARRIVE 100 40
 * ARRIVE 300 40
 * WITHDRAW 100
 * RESTORE
 *
 * Output:
 * 200
 * 700
 * 300
 *
 * Explanation:
 * Artifacts 200 and 700 have equal urgency, so the smaller ID,
 * 200, is restored first. Artifact 700 is restored next.
 * Artifact 100 is withdrawn, so artifact 300 is restored last.
 */
