// Week 10 - Problem 1: Busiest Bus Row
// Add up every row of the 2D grid and remember the row with the biggest total.
// Update the best row only when the total is strictly larger, so on a tie the smaller index stays.
//
// Time complexity : O(r * c)  - every seat is visited exactly once
// Extra space     : O(1)      - only a few variables

public class Problem1 {

    static void busiestRow(int[][] grid) {
        int bestRow = 0;
        int bestTotal = -1;

        for (int i = 0; i < grid.length; i++) {
            int total = 0;
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];
            }
            if (total > bestTotal) {      // strictly larger only
                bestTotal = total;
                bestRow = i;
            }
        }
        System.out.println("Row " + bestRow + ", Total " + bestTotal);
    }

    public static void main(String[] args) {
        // Sample
        int[][] grid = {
            {2, 0, 1},
            {3, 3, 1},
            {1, 1, 1}
        };
        busiestRow(grid);                       // Row 1, Total 7

        // my own input (tie between row 0 and row 2 -> smaller index wins)
        int[][] mine = {
            {3, 3, 0},
            {1, 0, 0},
            {2, 2, 2}
        };
        busiestRow(mine);                       // Row 0, Total 6
    }
}
