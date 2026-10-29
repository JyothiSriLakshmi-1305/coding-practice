/*
Problem: Running Maximum Prestige

Given artworks with a due day and prestige score, find the
maximum total prestige possible after each arrival. Each
artwork takes one day, and prestige is earned only when it
is completed by its due day.

Approach:
- Use a min-heap to store selected prestige scores.
- Add each new artwork and update the total.
- If the number of selected artworks exceeds its due day,
  remove the artwork with the lowest prestige.
- Record the maximum total after every arrival.

Time Complexity: O(N log N)
Space Complexity: O(N)

Sample Input 1:
6
2 10
2 20
1 5
3 30
1 40
2 7

Sample Output 1:
10 30 30 60 90 90

Sample Input 2:
5
1000000000 3
3 8
3 6
3 9
2 7

Sample Output 2:
3 11 17 26 27
*/

import java.io.*;
import java.util.*;

public class RunningMaximumPrestige {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        long total = 0;

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long dueDay = Long.parseLong(st.nextToken());
            long prestige = Long.parseLong(st.nextToken());

            minHeap.offer(prestige);
            total += prestige;

            if (minHeap.size() > dueDay) {
                total -= minHeap.poll();
            }

            if (i > 0) {
                output.append(' ');
            }

            output.append(total);
        }

        System.out.println(output);
    }
}
