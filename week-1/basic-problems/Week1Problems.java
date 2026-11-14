public class Week1Problems {

    // 1. exam hall seat duplicate checker (arrays + loops only)
    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean found = false;
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    // 2. typing speed accuracy checker
    static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatch = -1;
        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }
        double accuracy = (matched * 100.0) / total;
        String result = "Matched: " + matched + "/" + total
                + " | Accuracy: " + String.format("%.2f", accuracy) + "%";
        if (firstMismatch == -1) {
            result += " | No Mismatches";
        } else {
            result += " | First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')";
        }
        System.out.println(result);
    }

    // 3. traffic signal longest streak
    static void findLongestStreak(String signalLog) {
        char bestColor = signalLog.charAt(0);
        int bestLen = 1;
        int currentLen = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentLen++;
            } else {
                currentLen = 1;
            }
            if (currentLen > bestLen) {
                bestLen = currentLen;
                bestColor = signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: '" + bestColor + "' repeated " + bestLen + " times");
    }

    // 4. warehouse inventory balancer
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;
        for (int q : sectionA) totalA += q;
        for (int q : sectionB) totalB += q;

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int highest = sectionA[0];
        String where = "Section A";
        int index = 1;
        // first occurrence wins when there is a tie, so A is checked first
        for (int i = 1; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                where = "Section A";
                index = i + 1;
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                where = "Section B";
                index = i + 1;
            }
        }

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB
                + " | Status: " + status + " | Highest Quantity: " + highest
                + " (" + where + ", Item " + index + ")");
    }

    // 5. movie review word length profiler
    static void classifyWordLengths(String review) {
        String[] words = review.split(" ");
        int shortCount = 0, mediumCount = 0, longCount = 0;
        for (String w : words) {
            int len = w.length();
            if (len == 0) continue;
            if (len <= 4) {
                shortCount++;
            } else if (len <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }
        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 ---");
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});

        System.out.println("--- Problem 2 ---");
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");

        System.out.println("--- Problem 3 ---");
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");

        System.out.println("--- Problem 4 ---");
        analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});

        System.out.println("--- Problem 5 ---");
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
