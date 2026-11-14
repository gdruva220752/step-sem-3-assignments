// Week 10 - Problem 5: Second-Best Score
// Find the second-highest DISTINCT score without sorting.
// Keep two variables: highest and second. Go through the array once.
//   - bigger than highest      -> old highest becomes second, this one becomes highest
//   - smaller than highest but bigger than second -> it becomes second
//   - equal to highest         -> ignore (not a distinct score)
// If second never got a value, return -1.
//
// Time complexity : O(n)  - one pass
// Space complexity: O(1)  - two variables
// Sorting first would cost O(n log n) time (and changes the array), so one pass is better.

public class Problem5 {

    static int secondHighest(int[] scores) {
        int highest = -1;      // scores are >= 0, so -1 means "not found yet"
        int second = -1;

        for (int i = 0; i < scores.length; i++) {
            int s = scores[i];
            if (s > highest) {
                second = highest;
                highest = s;
            } else if (s < highest && s > second) {
                second = s;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        // Sample 1
        System.out.println(secondHighest(new int[]{45, 78, 92, 78, 60}));   // 78
        // Sample 2
        System.out.println(secondHighest(new int[]{50, 50, 50}));           // -1

        // my own inputs
        System.out.println(secondHighest(new int[]{7}));                    // -1
        System.out.println(secondHighest(new int[]{100, 0}));               // 0
        System.out.println(secondHighest(new int[]{10, 20, 20, 30, 30}));   // 20
        System.out.println(secondHighest(new int[]{0, 0, 5}));              // 0
    }
}
