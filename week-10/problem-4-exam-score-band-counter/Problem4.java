// Week 10 - Problem 4: Exam Score Band Counter
// The scores array is sorted, so we do NOT scan it one by one. Two binary searches are used:
//   firstIndex(scores, low)       -> first position where score >= low
//   firstIndex(scores, high + 1)  -> first position where score >= high + 1  (i.e. first score > high)
// Answer = (second position) - (first position).
//
// Complexity:
//   Linear scan  : O(n) time, O(1) space.
//   This solution: O(log n) time (two binary searches), O(1) space.
// Duplicates at the edges: a plain binary search can stop on any copy of low or high.
// That is why we search for the FIRST position with score >= x (we keep moving left on equal values)
// instead of stopping at the first match. Then every copy of low and high is counted correctly.

public class Problem4 {

    // first index i such that scores[i] >= x  (returns scores.length if none)
    static int firstIndex(int[] scores, int x) {
        int lo = 0;
        int hi = scores.length;          // answer is in [0, n]
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (scores[mid] >= x) {
                hi = mid;                // maybe an even earlier one exists, keep going left
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    static int countInBand(int[] scores, int low, int high) {
        return firstIndex(scores, high + 1) - firstIndex(scores, low);
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        // Sample 1
        System.out.println(countInBand(scores, 42, 58));    // 6
        // Sample 2
        System.out.println(countInBand(scores, 90, 100));   // 0

        // my own inputs
        System.out.println(countInBand(scores, 0, 100));    // 10
        System.out.println(countInBand(scores, 59, 62));    // 0
        System.out.println(countInBand(scores, 58, 58));    // 3
        System.out.println(countInBand(new int[]{5}, 5, 5));// 1
    }
}
