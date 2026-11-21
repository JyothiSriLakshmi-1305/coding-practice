/*
 * Day 01 - LeetCode 844: Backspace String Compare
 *
 * Problem:
 * Given two strings s and t, compare them after applying backspaces.
 * The '#' character represents a backspace. It removes the previous
 * character if one exists.
 *
 * Examples:
 * 1. s = "ab#c", t = "ad#c" -> true
 *    Both strings become "ac".
 *
 * 2. s = "ab##", t = "c#d#" -> true
 *    Both strings become empty.
 *
 * 3. s = "a#c", t = "b" -> false
 *    The strings become "c" and "b".
 *
 * Techniques Used:
 * 1. Two Pointers
 * 2. Reverse Traversal
 * 3. Backspace Handling
 * 4. Space Optimization
 *
 * Approach:
 * - Start traversing both strings from the end.
 * - Use separate skip counters for each string.
 * - When '#' is found, increase the skip counter.
 * - Skip normal characters while the counter is greater than zero.
 * - Compare the next valid characters.
 * - Return false if they differ; otherwise continue.
 *
 * Time Complexity: O(N + M)
 * Space Complexity: O(1)
 *
 * Key Learning:
 * Traversing strings backwards helps handle backspaces efficiently
 * without creating additional strings.
 */

public class BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        while(i >= 0 || j >= 0) {
            int skipS = 0;
            int skipT = 0;

            while(i >= 0) {
                if(s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if(skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            while(j >= 0) {
                if(t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if(skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            if(i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }

            if((i >= 0) != (j >= 0)) {
                return false;
            }

            i--;
            j--;
        }

        return true;
    }
}
