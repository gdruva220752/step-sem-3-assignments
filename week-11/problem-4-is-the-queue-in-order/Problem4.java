// Week 11 - Problem 4: Is the Queue in Order?
// One pass: compare each token with the one just before it.
// Return the index of the first student whose token is smaller than the previous one, else -1.

public class Problem4 {

    static int firstBreak(int[] tokens) {
        // empty queue or a single student is always in order
        if (tokens.length < 2) {
            return -1;
        }
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i] < tokens[i - 1]) {   // equal tokens are allowed, so only "<"
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Sample inputs
        System.out.println(firstBreak(new int[]{3, 5, 5, 8, 6, 9}));   // 4
        System.out.println(firstBreak(new int[]{1, 2, 2, 7}));         // -1

        // my own inputs
        System.out.println(firstBreak(new int[]{10, 4, 7}));           // 1
        System.out.println(firstBreak(new int[]{}));                   // -1
        System.out.println(firstBreak(new int[]{42}));                 // -1
    }
}
