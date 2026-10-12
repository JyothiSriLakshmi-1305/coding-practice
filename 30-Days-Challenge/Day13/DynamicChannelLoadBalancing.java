import java.util.*;

/*
 * Day 13: Dynamic Channel Load Balancing
 *
 * Aim:
 * Assign transmission requests to the least-loaded channel
 * and efficiently process request withdrawals.
 *
 * Problem Statement:
 * There are K channels, initially with zero load.
 *
 * Type 1: 1 id d
 * Assign the request to the channel with the smallest load.
 * If loads are tied, choose the channel with the smallest number.
 * Increase its load by d and print the assigned channel.
 *
 * Type 2: 2 id
 * Withdraw an active request, subtract its duration from
 * its assigned channel, and print the channel number and
 * its remaining load.
 *
 * Approach:
 * 1. TreeSet orders channels by load, then channel number.
 * 2. HashMap stores each active request's channel and duration.
 * 3. A channel is removed from TreeSet before its load changes.
 * 4. The updated channel is reinserted to maintain sorted order.
 *
 * Time Complexity: O((K + Q) log K)
 * Space Complexity: O(K + Q)
 */

public class DynamicChannelLoadBalancing {

    static class Channel {
        int channelNumber;
        long load;

        Channel(int channelNumber) {
            this.channelNumber = channelNumber;
            this.load = 0;
        }
    }

    static class Request {
        int channelNumber;
        long duration;

        Request(int channelNumber, long duration) {
            this.channelNumber = channelNumber;
            this.duration = duration;
        }
    }

    public static List<String> processEvents(
            int K, int Q, List<int[]> events) {

        List<String> results = new ArrayList<>();

        // Sort channels by load, then by channel number
        TreeSet<Channel> channels = new TreeSet<>((a, b) -> {
            int comparison = Long.compare(a.load, b.load);

            if (comparison != 0) {
                return comparison;
            }

            return Integer.compare(
                a.channelNumber,
                b.channelNumber
            );
        });

        // Store each active request by its ID
        Map<Integer, Request> activeRequests = new HashMap<>();

        // Direct access to each channel
        Channel[] channelByNumber = new Channel[K + 1];

        // Initially, all channels have zero load
        for (int i = 1; i <= K; i++) {
            Channel channel = new Channel(i);

            channelByNumber[i] = channel;
            channels.add(channel);
        }

        // Process events in their original order
        for (int[] event : events) {

            int type = event[0];
            int id = event[1];

            if (type == 1) {

                long duration = event[2];

                // Select the least-loaded channel
                Channel channel = channels.pollFirst();

                // Update its load
                channel.load += duration;

                // Record the request assignment
                activeRequests.put(
                    id,
                    new Request(channel.channelNumber, duration)
                );

                // Reinsert the updated channel
                channels.add(channel);

                results.add(
                    String.valueOf(channel.channelNumber)
                );

            } else if (type == 2) {

                // Retrieve and remove the active request
                Request request = activeRequests.remove(id);

                int channelNumber = request.channelNumber;
                long duration = request.duration;

                Channel channel = channelByNumber[channelNumber];

                // Remove before changing a TreeSet sort key
                channels.remove(channel);

                // Release the withdrawn request's load
                channel.load -= duration;

                // Reinsert using the updated load
                channels.add(channel);

                results.add(
                    channelNumber + " " + channel.load
                );
            }
        }

        return results;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt();
        int Q = scanner.nextInt();

        List<int[]> events = new ArrayList<>();

        for (int i = 0; i < Q; i++) {

            int type = scanner.nextInt();

            if (type == 1) {
                int id = scanner.nextInt();
                int d = scanner.nextInt();

                events.add(new int[]{type, id, d});

            } else {
                int id = scanner.nextInt();

                events.add(new int[]{type, id, 0});
            }
        }

        List<String> results = processEvents(K, Q, events);

        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}

/*
Input
2 4
1 201 10
1 202 4
2 201
1 203 6


Output
1
2
1 0
1
*/
