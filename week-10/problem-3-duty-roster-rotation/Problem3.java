// Week 10 - Problem 3: Duty Roster Rotation
// Rotate the roster to the right by k places.
// k can be huge (up to 10^9), so first remove the full turns with k % n.
// Then every name at index i moves to index (i + k) % n in a new array.
//
// Time complexity : O(n)  - each name is moved exactly once
// Space complexity: O(n)  - one extra array for the result
// Rotating one place at a time, k times, would cost O(n * k), which is far too slow when k = 10^9.

import java.util.Arrays;

public class Problem3 {

    static String[] rotateRoster(String[] names, int k) {
        int n = names.length;
        String[] result = new String[n];
        k = k % n;                       // full turns change nothing

        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = names[i];
        }
        return result;
    }

    public static void main(String[] args) {
        // Sample 1
        String[] a = {"A", "B", "C", "D", "E"};
        System.out.println(Arrays.toString(rotateRoster(a, 2)));    // [D, E, A, B, C]

        // Sample 2
        System.out.println(Arrays.toString(rotateRoster(a, 7)));    // [D, E, A, B, C]

        // my own inputs
        String[] b = {"Ravi", "Meena", "Kiran"};
        System.out.println(Arrays.toString(rotateRoster(b, 0)));          // [Ravi, Meena, Kiran]
        System.out.println(Arrays.toString(rotateRoster(b, 1000000000))); // [Kiran, Ravi, Meena]
    }
}
