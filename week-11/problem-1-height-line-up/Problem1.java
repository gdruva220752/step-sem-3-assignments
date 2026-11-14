// Week 11 - Problem 1: The Height Line-Up
// Bubble sort (no Arrays.sort). Print the line after every sweep.
// If a sweep makes no swap, the line is already sorted: print it and stop.

import java.util.Arrays;

public class Problem1 {

    static void lineUp(int[] heights) {
        int n = heights.length;

        for (int pass = 1; pass <= n - 1; pass++) {
            boolean swapped = false;          // flag to remember if any swap happened

            // last (pass-1) students are already at their place, so skip them
            for (int j = 0; j < n - pass; j++) {
                if (heights[j] > heights[j + 1]) {
                    int temp = heights[j];    // swap two neighbours
                    heights[j] = heights[j + 1];
                    heights[j + 1] = temp;
                    swapped = true;
                }
            }

            System.out.println("Pass " + pass + ": " + Arrays.toString(heights));

            if (!swapped) {                   // no swap -> already in order, stop
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Sample 1
        lineUp(new int[]{4, 2, 5, 1});
        System.out.println();

        // Sample 2
        lineUp(new int[]{1, 3, 2, 4});
        System.out.println();

        // my own input
        lineUp(new int[]{9, 7, 3, 3, 8, 1});
    }
}
