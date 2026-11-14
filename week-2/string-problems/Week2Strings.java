import java.util.*;

public class Week2Strings {

    // 1. ATM PIN length check
    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    // 2. reverse every word, keep word order
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            result.append(sb.reverse());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    // 3. inventory csv line parser
    static void parseInventoryRecord(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Product: " + parts[0] + " | SKU: " + parts[1] + " | Qty: " + parts[2]);
    }

    // 4a. trim spaces, uppercase only the first 3 chars
    static String normalizeCode(String raw) {
        String t = raw.trim();
        if (t.length() < 3) {
            return t.toUpperCase();
        }
        return t.substring(0, 3).toUpperCase() + t.substring(3);
    }

    // 4b. validate and format
    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit character in year/catalog part";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(code.substring(0, 3)).append("]");
        sb.append(" YEAR: ").append(code.substring(3, 7));
        sb.append(" | CATALOG: ").append(code.substring(7));
        return sb.toString();
    }

    // 5. word frequency without stop words
    static void printFilteredWordFrequency(String feedback) {
        List<String> stop = Arrays.asList("the", "was", "and", "a", "is", "of", "in");
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.split("\\s+");

        Map<String, Integer> freq = new LinkedHashMap<>();
        for (String w : words) {
            if (w.isEmpty() || stop.contains(w)) continue;
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        // sort by count, biggest first (stable, so ties keep first-seen order)
        entries.sort((x, y) -> y.getValue() - x.getValue());
        for (Map.Entry<String, Integer> e : entries) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 ---");
        checkPinLength("482");
        checkPinLength("4820");

        System.out.println("--- Problem 2 ---");
        System.out.println(reverseEachWord("hello club"));

        System.out.println("--- Problem 3 ---");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parseInventoryRecord("Wireless Mouse,150");

        System.out.println("--- Problem 4 ---");
        System.out.println(validateAndFormat(normalizeCode("  pen2026004251 ")));
        System.out.println(validateAndFormat(normalizeCode("12N2026004251")));
        System.out.println(validateAndFormat(normalizeCode("pen20260042")));

        System.out.println("--- Problem 5 ---");
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
