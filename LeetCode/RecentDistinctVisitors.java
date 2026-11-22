/*
 * Problem: Recent Distinct Visitors
 *
 * Description:
 * Given N badge scans and a time window T, find the number
 * of distinct badge numbers scanned during the most recent
 * T minutes after every scan.
 *
 * Also find the maximum distinct count and the earliest
 * minute at which that maximum occurs.
 *
 * Techniques Used:
 * 1. Sliding Window
 * 2. HashMap Frequency Counting
 * 3. Queue
 *
 * Approach:
 * - Maintain scans in chronological order using a queue.
 * - Calculate the starting minute of the active window.
 * - Remove expired scans from the front of the queue.
 * - Update badge frequencies using a HashMap.
 * - HashMap size gives the number of distinct badges.
 * - Update the maximum only when a strictly larger count
 *   is found to preserve the earliest minute.
 *
 * Time Complexity: O(N) expected
 * Space Complexity: O(N)
 *
 * Key Learning:
 * Sliding Window with HashMap frequency counting efficiently
 * handles duplicate values and time-based expiration.
 */

import java.io.*;
import java.util.*;

public class RecentDistinctVisitors {

    static class Scan {
        long minute;
        long badge;

        Scan(long minute, long badge) {
            this.minute = minute;
            this.badge = badge;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());

        HashMap<Long, Integer> frequency = new HashMap<>();
        Queue<Scan> window = new ArrayDeque<>();

        int[] result = new int[n];

        int maxDistinct = 0;
        long earliestMinute = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            long minute = Long.parseLong(st.nextToken());
            long badge = Long.parseLong(st.nextToken());

            long start = minute - t + 1;

            while(!window.isEmpty() && window.peek().minute < start) {
                Scan old = window.poll();

                int count = frequency.get(old.badge);

                if(count == 1) {
                    frequency.remove(old.badge);
                } else {
                    frequency.put(old.badge, count - 1);
                }
            }

            window.offer(new Scan(minute, badge));

            frequency.put(badge, frequency.getOrDefault(badge, 0) + 1);

            int distinct = frequency.size();
            result[i] = distinct;

            if(distinct > maxDistinct) {
                maxDistinct = distinct;
                earliestMinute = minute;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(maxDistinct).append(" ").append(earliestMinute).append("\n");

        for(int i = 0; i < n; i++) {
            if(i > 0) {
                sb.append(" ");
            }
            sb.append(result[i]);
        }

        System.out.println(sb);
    }
}
