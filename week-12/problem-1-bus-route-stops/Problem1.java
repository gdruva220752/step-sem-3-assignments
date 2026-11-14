// Problem 1: Bus Route Stops
// Each stop is stored in a node of my own linked list (no java.util.LinkedList)

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem1 {

    // builds the chain from the stop names in the same order
    static Node build(String[] stops) {
        Node head = null;
        Node tail = null;
        for (String s : stops) {
            Node n = new Node(s);
            if (head == null) {
                head = n;          // first stop becomes head
                tail = n;
            } else {
                tail.next = n;     // attach at the end
                tail = n;
            }
        }
        return head;
    }

    // prints the chain, then count, first and last stop
    static void printRoute(Node head) {
        Node cur = head;
        int count = 0;
        String last = "";
        StringBuilder sb = new StringBuilder();

        while (cur != null) {
            sb.append(cur.data).append(" -> ");
            count++;
            last = cur.data;       // last node we visit is the last stop
            cur = cur.next;
        }
        sb.append("null");

        System.out.println(sb);
        System.out.println("Stops: " + count + ", First: " + head.data + ", Last: " + last);
    }

    public static void main(String[] args) {
        // Sample 1
        String[] stops1 = {"Tambaram", "Guindy", "TNagar", "Central"};
        printRoute(build(stops1));

        System.out.println();

        // Sample 2
        String[] stops2 = {"Potheri"};
        printRoute(build(stops2));
    }
}
