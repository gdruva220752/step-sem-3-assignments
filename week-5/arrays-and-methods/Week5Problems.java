import java.util.Arrays;

// Week 5 - Java Arrays and Methods (Category C)
// Problems 1-4 are static helper methods, problem 5 uses a Player class with Comparable.

class Player implements Comparable<Player> {
    private String name;
    private int matchesPlayed;
    private double battingAverage;
    private boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    // experienced players get picked on matches alone
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    // everyone else needs some experience AND must be fit
    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    boolean canBeDrafted() {
        return isDraftable(matchesPlayed) || isDraftable(matchesPlayed, injured);
    }

    // higher batting average (fantasy points) comes first
    public int compareTo(Player other) {
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    String getName() {
        return name;
    }

    static String draftAndRank(Player[] players) {
        int count = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i].canBeDrafted()) {
                count++;
            }
        }
        Player[] draftable = new Player[count];
        int k = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i].canBeDrafted()) {
                draftable[k] = players[i];
                k++;
            }
        }
        Arrays.sort(draftable);

        String result = "";
        for (int i = 0; i < draftable.length; i++) {
            if (i > 0) {
                result = result + " | ";
            }
            result = result + (i + 1) + ". " + draftable[i].getName();
        }
        return result;
    }
}

public class Week5Problems {

    // ---------- Problem 1: Fantasy Team Score Multiplier ----------
    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] = playerScores[captainIndex] * 2;
        playerScores[viceCaptainIndex] = playerScores[viceCaptainIndex] * 1.5;
    }

    // ---------- Problem 2: Duplicate Player Pick Checker ----------
    static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // ---------- Problem 3: Top Performer Tracker ----------
    static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return "Min: " + min + " | Max: " + max + " | Spread: " + (max - min);
    }

    // ---------- Problem 4: Match Day Grid Analyzer ----------
    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {
        String result = "";
        for (int m = 0; m < runsPerOver.length; m++) {
            if (m > 0) {
                result = result + " | ";
            }
            if (rowAverage(runsPerOver[m]) >= threshold) {
                result = result + "Match " + m + ": Power Surge";
            } else {
                result = result + "Match " + m + ": Normal";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 ---");
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores));

        System.out.println("--- Problem 2 ---");
        System.out.println(findDuplicatePick(new String[]{"Kohli", "Bumrah", "Kohli", "Rohit"}));
        System.out.println(findDuplicatePick(new String[]{"Kohli", "Bumrah", "Rohit"}));

        System.out.println("--- Problem 3 ---");
        System.out.println(findMinMaxSpread(new int[]{45, 82, 79, 90, 33, 90, 61}));

        System.out.println("--- Problem 4 ---");
        int[][] grid = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        System.out.println(classifyMatches(grid, 8));

        System.out.println("--- Problem 5 ---");
        Player[] lineup = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };
        System.out.println(Player.draftAndRank(lineup));
    }
}
