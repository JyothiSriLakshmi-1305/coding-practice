import java.util.*;

/*
 * Day 24: Wildlife Crowding Alerts
 *
 * Problem:
 * Process wildlife sightings in chronological order.
 * For each sighting at time T, count sightings of the same
 * species in the inclusive time window [T - W, T].
 * Raise an alert when that count is at least K.
 *
 * Output:
 * 1. Total number of alerts.
 * 2. Time and species of the earliest alert, or QUIET.
 *
 * Approach:
 * 1. Maintain a queue of sightings within the current time window.
 * 2. Use a HashMap to track the frequency of each species.
 * 3. Remove sightings whose time is strictly less than T - W.
 * 4. Count an alert whenever the current species frequency >= K.
 *
 * Time Complexity: O(N) expected
 * Space Complexity: O(N)
 *
 * Sample Test Case 1
 *
 * Input:
 * 5 5 3
 * 1 1
 * 2 2
 * 8 1
 * 13 1
 * 14 2
 *
 * Output:
 * 0
 * QUIET
 *
 * Sample Test Case 2
 *
 * Input:
 * 8 10 3
 * 0 5
 * 3 7
 * 4 5
 * 9 5
 * 12 7
 * 14 5
 * 15 7
 * 30 5
 *
 * Output:
 * 2
 * 9 5
 */

public class WildlifeCrowdingAlerts {

    static class Sighting {
        int time;
        int species;

        Sighting(int time, int species) {
            this.time = time;
            this.species = species;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int windowSize = scanner.nextInt();
        int threshold = scanner.nextInt();

        Queue<Sighting> window = new ArrayDeque<>();
        Map<Integer, Integer> frequency = new HashMap<>();

        int alertCount = 0;
        int firstAlertTime = -1;
        int firstAlertSpecies = -1;

        for (int i = 0; i < n; i++) {
            int time = scanner.nextInt();
            int species = scanner.nextInt();

            // Remove sightings outside the inclusive time window.
            while (!window.isEmpty() &&
                   window.peek().time < time - windowSize) {

                Sighting old = window.poll();
                int count = frequency.get(old.species) - 1;

                if (count == 0) {
                    frequency.remove(old.species);
                } else {
                    frequency.put(old.species, count);
                }
            }

            // Add the current sighting.
            window.offer(new Sighting(time, species));

            int count = frequency.getOrDefault(species, 0) + 1;
            frequency.put(species, count);

            // Every sighting is evaluated independently.
            if (count >= threshold) {
                alertCount++;

                if (firstAlertTime == -1) {
                    firstAlertTime = time;
                    firstAlertSpecies = species;
                }
            }
        }

        System.out.println(alertCount);

        if (alertCount == 0) {
            System.out.println("QUIET");
        } else {
            System.out.println(
                firstAlertTime + " " + firstAlertSpecies
            );
        }

        scanner.close();
    }
}
