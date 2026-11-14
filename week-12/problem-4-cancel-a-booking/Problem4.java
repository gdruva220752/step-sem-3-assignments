// Problem 4: Cancel a Booking
// remove only the FIRST appearance of the booking number

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem4 {

    static Node removeFirst(Node head, String booking) {
        // empty chain -> nothing to cancel
        if (head == null) {
            System.out.println(booking + " not found");
            return head;
        }

        // booking is at the head, so just move head forward
        if (head.data.equals(booking)) {
            return head.next;
        }

        // otherwise stop at the node just before the match
        Node prev = head;
        while (prev.next != null && !prev.next.data.equals(booking)) {
            prev = prev.next;
        }

        if (prev.next == null) {            // reached the end, no match
            System.out.println(booking + " not found");
        } else {
            prev.next = prev.next.next;     // skip the matched node
        }
        return head;
    }

    static Node build(String[] items) {
        Node head = null, tail = null;
        for (String s : items) {
            Node n = new Node(s);
            if (head == null) { head = n; tail = n; }
            else { tail.next = n; tail = n; }
        }
        return head;
    }

    static void printChain(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        // Sample 1
        Node h1 = build(new String[]{"104", "101", "107", "101"});
        h1 = removeFirst(h1, "101");
        printChain(h1);

        // Sample 2
        Node h2 = build(new String[]{"104", "101", "107"});
        h2 = removeFirst(h2, "104");
        printChain(h2);

        // Sample 3
        Node h3 = build(new String[]{"104", "101"});
        h3 = removeFirst(h3, "200");
        printChain(h3);
    }
}
