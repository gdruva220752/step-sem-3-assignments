// Problem 3: Book Copy Finder
// one walk through the chain: find first position and count copies

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem3 {

    static String find(Node head, String id) {
        int position = 0;     // current position (starts from 1 after first move)
        int firstPos = -1;    // -1 means not seen yet
        int copies = 0;

        Node cur = head;
        while (cur != null) {
            position++;
            if (cur.data.equals(id)) {     // equals, not ==, for strings
                copies++;
                if (firstPos == -1) {
                    firstPos = position;
                }
            }
            cur = cur.next;
        }

        if (copies == 0) {
            return id + " not found";
        }
        return id + " first at position " + firstPos + ", copies: " + copies;
    }

    static Node build(String[] ids) {
        Node head = null, tail = null;
        for (String s : ids) {
            Node n = new Node(s);
            if (head == null) { head = n; tail = n; }
            else { tail.next = n; tail = n; }
        }
        return head;
    }

    public static void main(String[] args) {
        Node trolley = build(new String[]{"B12", "B07", "B30", "B07", "B45", "B07"});

        // Sample 1
        System.out.println(find(trolley, "B07"));
        // Sample 2
        System.out.println(find(trolley, "B99"));
    }
}
