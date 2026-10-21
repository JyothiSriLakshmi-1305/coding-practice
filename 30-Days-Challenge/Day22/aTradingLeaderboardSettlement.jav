import java.util.*;

/*
 * Day 22: Trading Leaderboard Settlement
 *
 * Problem:
 * Process BUY and PEAK events for ticker IDs.
 * BUY adds units to a ticker's accumulated total.
 * PEAK reports the ticker with the highest positive total,
 * then resets that ticker's total to zero.
 *
 * Tie-breaking:
 * If multiple tickers have the same total, choose the
 * ticker with the smaller ID.
 *
 * Approach:
 * 1. HashMap stores each ticker's current total.
 * 2. TreeSet maintains tickers ordered by total descending,
 *    then ticker ID ascending.
 * 3. On BUY, remove the old entry and insert the updated total.
 * 4. On PEAK, report the leader and reset its total.
 *
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 *
 * Sample Test Case 1
 *
 * Input:
 * 5
 * BUY 5 10
 * BUY 3 10
 * PEAK
 * BUY 3 5
 * PEAK
 *
 * Output:
 * 3 10
 * 5 10
 *
 * Sample Test Case 2
 *
 * Input:
 * 6
 * BUY 101 50
 * BUY 202 30
 * BUY 101 20
 * PEAK
 * BUY 202 10
 * PEAK
 *
 * Output:
 * 101 70
 * 202 40
 */

public class TradingLeaderboardSettlement {

    static class Event {
        String type;
        int id;
        int units;

        Event(String type, int id, int units) {
            this.type = type;
            this.id = id;
            this.units = units;
        }
    }

    static class Ticker {
        int id;
        long units;

        Ticker(int id, long units) {
            this.id = id;
            this.units = units;
        }
    }

    public static List<long[]> userLogic(int n, List<Event> events) {
        List<long[]> results = new ArrayList<>();
        Map<Integer, Long> totals = new HashMap<>();

        Comparator<Ticker> comparator = (a, b) -> {
            if (a.units != b.units) {
                return Long.compare(b.units, a.units);
            }
            return Integer.compare(a.id, b.id);
        };

        TreeSet<Ticker> leaderboard = new TreeSet<>(comparator);

        for (Event event : events) {
            if (event.type.equals("BUY")) {
                int id = event.id;
                long oldTotal = totals.getOrDefault(id, 0L);

                if (oldTotal > 0) {
                    leaderboard.remove(new Ticker(id, oldTotal));
                }

                long newTotal = oldTotal + event.units;
                totals.put(id, newTotal);
                leaderboard.add(new Ticker(id, newTotal));

            } else if (event.type.equals("PEAK")) {
                Ticker leader = leaderboard.first();

                results.add(new long[]{leader.id, leader.units});

                leaderboard.remove(leader);
                totals.put(leader.id, 0L);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\\s+");

            if (parts[0].equals("BUY")) {
                int id = Integer.parseInt(parts[1]);
                int units = Integer.parseInt(parts[2]);
                events.add(new Event("BUY", id, units));
            } else {
                events.add(new Event("PEAK", 0, 0));
            }
        }

        List<long[]> results = userLogic(n, events);

        for (long[] result : results) {
            System.out.println(result[0] + " " + result[1]);
        }

        scanner.close();
    }
}
