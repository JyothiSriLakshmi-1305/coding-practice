import java.util.*;

/*
 * Day 15: Dynamic Maximum XOR Interference
 *
 * Aim:
 * Process animal registrations, retirements, and ping queries
 * to find maximum XOR interference among active animals.
 *
 * Problem Statement:
 * REGISTER name code:
 * Register an animal with its fixed signal code.
 *
 * RETIRE name:
 * Remove an animal from active monitoring.
 *
 * PING name:
 * Find the maximum XOR between the specified animal's code
 * and the code of any other active animal. Print -1 if no
 * other active animal exists.
 *
 * Maintain the K largest valid ping results and print them
 * in descending order after all events are processed.
 *
 * Approach:
 * 1. Binary Trie stores active signal codes.
 * 2. Trie node counts support insertion and deletion.
 * 3. HashMap maps active animal names to their codes.
 * 4. Temporarily remove the queried code to exclude the
 *    animal from comparison with itself.
 * 5. A min-heap maintains the K largest valid XOR results.
 *
 * Time Complexity:
 * O(N * (20 + log K)) for K > 0; O(20N) when K = 0.
 *
 * Space Complexity:
 * O(20N + K)
 */

public class DynamicMaximumXORInterference {

    static class TrieNode {
        TrieNode[] child = new TrieNode[2];
        int count;
    }

    static class BinaryTrie {

        TrieNode root = new TrieNode();

        // Insert or remove a signal code
        void update(int value, int delta) {

            TrieNode current = root;
            current.count += delta;

            for (int bit = 19; bit >= 0; bit--) {

                int direction = (value >> bit) & 1;

                if (current.child[direction] == null) {
                    current.child[direction] = new TrieNode();
                }

                current = current.child[direction];
                current.count += delta;
            }
        }

        // Find the maximum XOR with an active signal code
        int maxXor(int value) {

            TrieNode current = root;
            int result = 0;

            for (int bit = 19; bit >= 0; bit--) {

                int direction = (value >> bit) & 1;
                int opposite = 1 - direction;

                if (current.child[opposite] != null &&
                    current.child[opposite].count > 0) {

                    result |= (1 << bit);
                    current = current.child[opposite];

                } else {
                    current = current.child[direction];
                }
            }

            return result;
        }
    }

    public static void processLogEntries(
            int n,
            int k,
            List<String[]> logEntries,
            List<Integer> interferenceValues,
            List<Integer> shortlist) {

        BinaryTrie trie = new BinaryTrie();

        Map<String, Integer> activeAnimals = new HashMap<>();

        // Keeps the K largest valid interference values
        PriorityQueue<Integer> topK = new PriorityQueue<>();

        for (String[] entry : logEntries) {

            String operation = entry[0];
            String name = entry[1];

            if (operation.equals("REGISTER")) {

                int code = Integer.parseInt(entry[2]);

                activeAnimals.put(name, code);
                trie.update(code, 1);

            } else if (operation.equals("RETIRE")) {

                int code = activeAnimals.remove(name);

                trie.update(code, -1);

            } else if (operation.equals("PING")) {

                int code = activeAnimals.get(name);

                // No other active animal is available
                if (activeAnimals.size() <= 1) {

                    interferenceValues.add(-1);

                } else {

                    // Exclude the queried animal from comparison
                    trie.update(code, -1);

                    int maximum = trie.maxXor(code);

                    // Restore its code to the trie
                    trie.update(code, 1);

                    interferenceValues.add(maximum);

                    // Maintain the K largest valid results
                    if (k > 0) {

                        if (topK.size() < k) {
                            topK.offer(maximum);

                        } else if (maximum > topK.peek()) {
                            topK.poll();
                            topK.offer(maximum);
                        }
                    }
                }
            }
        }

        // Produce the final shortlist in descending order
        while (!topK.isEmpty()) {
            shortlist.add(topK.poll());
        }

        Collections.reverse(shortlist);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();

        List<String[]> logEntries = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            logEntries.add(
                scanner.nextLine().trim().split("\\s+")
            );
        }

        List<Integer> interferenceValues = new ArrayList<>();
        List<Integer> shortlist = new ArrayList<>();

        processLogEntries(
            n,
            k,
            logEntries,
            interferenceValues,
            shortlist
        );

        for (int value : interferenceValues) {
            System.out.println(value);
        }

        if (shortlist.isEmpty()) {

            System.out.println("NONE");

        } else {

            for (int i = 0; i < shortlist.size(); i++) {

                if (i > 0) {
                    System.out.print(" ");
                }

                System.out.print(shortlist.get(i));
            }

            System.out.println();
        }

        scanner.close();
    }
}

/*
 * ==================== SAMPLE TEST CASE 0 ====================
 *
 * Input:
 * 7 2
 * REGISTER falcon 12
 * REGISTER lynx 9
 * PING falcon
 * REGISTER otter 5
 * PING lynx
 * RETIRE falcon
 * PING otter
 *
 * Output:
 * 5
 * 12
 * 12
 * 12 12
 *
 * Explanation:
 * 12 XOR 9 = 5.
 * For code 9, the maximum of (9 XOR 12) and (9 XOR 5)
 * is 12.
 * After falcon retires, 5 XOR 9 = 12.
 * The two largest recorded values are 12 and 12.
 *
 *
 * ==================== SAMPLE TEST CASE 1 ====================
 *
 * Input:
 * 5 1
 * REGISTER hawk 7
 * PING hawk
 * REGISTER kestrel 3
 * PING hawk
 * RETIRE kestrel
 *
 * Output:
 * -1
 * 4
 * 4
 *
 * Explanation:
 * The first ping has no other active animal, so it returns -1.
 * After kestrel registers, 7 XOR 3 = 4.
 * The final shortlist contains only the largest valid value: 4.
 */
