/*
 * Day 06: Live Species Frequency Tracker
 *
 * Problem:
 * Process species sightings and snapshot requests.
 * Display the top C species based on frequency.
 * If frequencies are equal, sort alphabetically.
 *
 * Approach:
 * HashMap + TreeSet
 * Time Complexity: O(n log D + R * C)
 * Space Complexity: O(D + R)
 */

import java.util.*;

public class SpeciesFrequencyTracker {

    static class Species {
        String name;
        int count;

        Species(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    public static List<String> processStream(int n, int C, List<String[]> commands) {

        Map<String, Integer> frequency = new HashMap<>();

        TreeSet<Species> ranking = new TreeSet<>((a, b) -> {
            int compare = Integer.compare(b.count, a.count);

            if (compare != 0) {
                return compare;
            }

            return a.name.compareTo(b.name);
        });

        Map<String, Species> speciesMap = new HashMap<>();

        List<String> results = new ArrayList<>();

        for (String[] command : commands) {

            if (command[0].equals("S")) {
                String name = command[1];

                Species oldSpecies = speciesMap.get(name);

                if (oldSpecies != null) {
                    ranking.remove(oldSpecies);
                }

                int newCount = frequency.getOrDefault(name, 0) + 1;
                frequency.put(name, newCount);

                Species updatedSpecies = new Species(name, newCount);

                speciesMap.put(name, updatedSpecies);
                ranking.add(updatedSpecies);
            }

            else if (command[0].equals("R")) {
                StringBuilder snapshot = new StringBuilder();

                int count = 0;

                for (Species species : ranking) {
                    if (count == C) {
                        break;
                    }

                    if (count > 0) {
                        snapshot.append(" ");
                    }

                    snapshot.append(species.name);
                    count++;
                }

                results.add(snapshot.toString());
            }
        }

        return results;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int C = scanner.nextInt();

        scanner.nextLine();

        List<String[]> commands = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");

            commands.add(parts);
        }

        List<String> results = processStream(n, C, commands);

        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}

/*
 * Sample Test Case 1:
 *
 * Input:
 * 7 2
 * S dog
 * S cat
 * S bat
 * R
 * S cat
 * S cat
 * R
 *
 * Output:
 * bat cat
 * cat bat
 *
 * Sample Test Case 2:
 *
 * Input:
 * 8 2
 * S owl
 * S owl
 * S fox
 * R
 * S fox
 * S fox
 * R
 * S owl
 *
 * Output:
 * owl fox
 * fox owl
 */
