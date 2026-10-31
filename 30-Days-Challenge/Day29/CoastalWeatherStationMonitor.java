/*
Problem: Coastal Weather Station Monitor

Track each station's latest temperature reading. A reading
remains valid for T minutes. For every inquiry, report the
warmest active station, breaking ties alphabetically by name.
If no reading is active, print NONE.

Approach:
- HashMap stores each station's latest reading.
- TreeSet orders readings by temperature descending,
  then station name ascending.
- PriorityQueue processes reading expirations efficiently.
- Version numbers prevent outdated expiry entries from
  removing a station's newer reading.

Time Complexity: O(N log N)
Space Complexity: O(N)

Sample Input 1:
7 10
R 1 alpha 30
R 2 beta 35
Q 3
R 5 alpha 40
Q 6
Q 12
Q 15

Sample Output 1:
beta 35
alpha 40
alpha 40
NONE

Sample Input 2:
8 5
R 1 delta 50
R 1 coral 50
Q 1
R 2 coral 20
Q 2
R 4 delta 10
Q 4
Q 7

Sample Output 2:
coral 50
delta 50
coral 20
delta 10
*/

import java.io.*;
import java.util.*;

public class CoastalWeatherStationMonitor {

    static class Reading {
        String name;
        int temperature;
        long expiry;
        long version;

        Reading(String name, int temperature,
                long expiry, long version) {
            this.name = name;
            this.temperature = temperature;
            this.expiry = expiry;
            this.version = version;
        }
    }

    static class ExpiryEntry {
        String name;
        long expiry;
        long version;

        ExpiryEntry(String name, long expiry, long version) {
            this.name = name;
            this.expiry = expiry;
            this.version = version;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        Map<String, Reading> latest = new HashMap<>();

        TreeSet<Reading> active = new TreeSet<>((a, b) -> {
            if (a.temperature != b.temperature) {
                return Integer.compare(b.temperature, a.temperature);
            }
            return a.name.compareTo(b.name);
        });

        PriorityQueue<ExpiryEntry> expiryQueue =
                new PriorityQueue<>((a, b) -> {
                    int cmp = Long.compare(a.expiry, b.expiry);
                    if (cmp != 0) return cmp;
                    return Long.compare(a.version, b.version);
                });

        StringBuilder output = new StringBuilder();
        long version = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            long time = Long.parseLong(st.nextToken());

            while (!expiryQueue.isEmpty()
                    && expiryQueue.peek().expiry <= time) {

                ExpiryEntry entry = expiryQueue.poll();
                Reading current = latest.get(entry.name);

                if (current != null
                        && current.version == entry.version
                        && current.expiry == entry.expiry) {
                    active.remove(current);
                    latest.remove(entry.name);
                }
            }

            if (type.equals("R")) {
                String name = st.nextToken();
                int temperature = Integer.parseInt(st.nextToken());

                Reading previous = latest.remove(name);
                if (previous != null) {
                    active.remove(previous);
                }

                long expiry = time + T;
                long currentVersion = ++version;

                Reading reading = new Reading(
                        name, temperature, expiry, currentVersion);

                latest.put(name, reading);
                active.add(reading);

                expiryQueue.offer(new ExpiryEntry(
                        name, expiry, currentVersion));

            } else if (type.equals("Q")) {
                if (active.isEmpty()) {
                    output.append("NONE\n");
                } else {
                    Reading best = active.first();
                    output.append(best.name)
                          .append(' ')
                          .append(best.temperature)
                          .append('\n');
                }
            }
        }

        System.out.print(output);
    }
}
