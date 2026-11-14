// Week 11 - Problem 5: The Dice Throw Tally
// Counting sort. Count how many times each number 1..6 appeared,
// then rebuild the results in increasing order (no comparing of two throws).

import java.util.Arrays;

public class Problem5 {

    static int[] diceSort(int[] rolls) {
        int[] count = new int[7];             // index 1..6 is used, index 0 is not used

        // step 1: count each throw
        for (int i = 0; i < rolls.length; i++) {
            count[rolls[i]]++;
        }

        // print the counts
        StringBuilder sb = new StringBuilder("Counts:");
        for (int v = 1; v <= 6; v++) {
            sb.append(" ").append(v).append(":").append(count[v]);
        }
        System.out.println(sb);

        // step 2: write each value v into the result count[v] times
        int[] result = new int[rolls.length];
        int pos = 0;
        for (int v = 1; v <= 6; v++) {
            for (int k = 0; k < count[v]; k++) {
                result[pos++] = v;
            }
        }
        System.out.println("Sorted: " + Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        // Sample
        diceSort(new int[]{4, 1, 6, 4, 2, 1, 4});
        System.out.println();

        // my own input
        diceSort(new int[]{6, 6, 3, 5, 3, 3});
    }
}
