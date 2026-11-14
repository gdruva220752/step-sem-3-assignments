// Problem 2: Late Comers in the Line
// line is a chain of names. Pass holder -> front, others -> end.

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem2 {

    // new node goes in front of the old head
    static Node addFirst(Node head, String name) {
        Node n = new Node(name);
        n.next = head;
        return n;                  // new head
    }

    // walk to the last node and attach there
    static Node addLast(Node head, String name) {
        Node n = new Node(name);
        if (head == null) {        // empty line, new node is the head
            return n;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = n;
        return head;
    }

    static void printLine(Node head) {
        Node cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.data).append(" -> ");
            cur = cur.next;
        }
        sb.append("null");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        // Sample 1: line = Asha -> Ravi
        Node line = new Node("Asha");
        line.next = new Node("Ravi");

        line = addFirst(line, "Neha");
        line = addLast(line, "Kiran");
        line = addFirst(line, "Mohan");
        printLine(line);

        // Sample 2: empty line
        Node line2 = null;
        line2 = addLast(line2, "Pooja");
        line2 = addFirst(line2, "Arun");
        printLine(line2);
    }
}
