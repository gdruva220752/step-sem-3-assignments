// Week 11 - Problem 3: The Library Book Shelf
// Insertion sort (no Arrays.sort). Print the shelf after placing each book
// from the second one onwards.

import java.util.Arrays;

public class Problem3 {

    static void shelf(int[] books) {
        for (int i = 1; i < books.length; i++) {
            int newBook = books[i];           // keep the new book in a variable
            int j = i - 1;

            // move every bigger book on the left one place to the right
            while (j >= 0 && books[j] > newBook) {
                books[j + 1] = books[j];
                j--;
            }
            books[j + 1] = newBook;           // put the new book into the gap

            System.out.println("After placing " + newBook + ": " + Arrays.toString(books));
        }
    }

    public static void main(String[] args) {
        // Sample
        shelf(new int[]{30, 10, 40, 20});
        System.out.println();

        // my own input
        shelf(new int[]{5, 3, 8, 3, 1});
    }
}
