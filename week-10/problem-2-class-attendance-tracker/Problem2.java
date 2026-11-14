// Week 10 - Problem 2: Class Attendance Tracker
// One pass over the days array:
//   - count every 1 (present days)
//   - keep a running streak that grows on 1 and resets to 0 on 0, remember the biggest streak
//
// Time complexity : O(n)  - single pass, n = number of days
// Space complexity: O(1)  - only a few counters
// Yes, both answers are found in the same single pass.

public class Problem2 {

    static void attendanceSummary(int[] days) {
        int present = 0;
        int streak = 0;
        int longest = 0;

        for (int i = 0; i < days.length; i++) {
            if (days[i] == 1) {
                present++;
                streak++;
                if (streak > longest) {
                    longest = streak;
                }
            } else {
                streak = 0;
            }
        }
        System.out.println("Present: " + present + ", Longest streak: " + longest);
    }

    public static void main(String[] args) {
        // Sample 1
        attendanceSummary(new int[]{1, 1, 0, 1, 1, 1, 0, 1});   // Present: 6, Longest streak: 3
        // Sample 2
        attendanceSummary(new int[]{0, 0, 0});                  // Present: 0, Longest streak: 0

        // my own inputs
        attendanceSummary(new int[]{1, 1, 1, 1});               // Present: 4, Longest streak: 4
        attendanceSummary(new int[]{1, 0, 1, 1, 0, 1});         // Present: 4, Longest streak: 2
    }
}
