// Week 11 - Problem 2: The Prize Table
// Selection sort in descending order (no Arrays.sort).
// Returns the number of swaps that were actually made.

import java.util.Arrays;

public class Problem2 {

    static int prizeTable(int[] marks) {
        int swaps = 0;
        int n = marks.length;

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;                     // assume position i has the highest

            // scan from i + 1 to the end to find the index of the largest mark
            for (int j = i + 1; j < n; j++) {
                if (marks[j] > marks[maxIndex]) {
                    maxIndex = j;
                }
            }

            // swap only if the highest mark is not already at position i
            if (maxIndex != i) {
                int temp = marks[i];
                marks[i] = marks[maxIndex];
                marks[maxIndex] = temp;
                swaps++;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        // Sample 1
        int[] a = {45, 90, 60, 75};
        int s1 = prizeTable(a);
        System.out.println(Arrays.toString(a) + "   swaps = " + s1);

        // Sample 2
        int[] b = {80, 70, 60};
        int s2 = prizeTable(b);
        System.out.println(Arrays.toString(b) + "       swaps = " + s2);

        // my own input (has a repeated mark)
        int[] c = {10, 50, 50, 20, 30};
        int s3 = prizeTable(c);
        System.out.println(Arrays.toString(c) + "   swaps = " + s3);
    }
}
