// Problem 5: Plate Stack on a Chain
// top of the stack = head of the chain, so push/pop are both at the head

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Problem5 {

    private Node top = null;   // head of chain
    private int size = 0;

    void push(String plate) {
        Node n = new Node(plate);
        n.next = top;          // new node points to old top
        top = n;
        size++;
        System.out.println("push " + plate + " -> top " + top.data + ", size " + size);
    }

    String pop() {
        if (isEmptyQuiet()) {          // check before removing
            System.out.println("pop -> stack is empty");
            return null;
        }
        String removed = top.data;
        top = top.next;
        size--;
        System.out.println("pop -> " + removed + ", size " + size);
        return removed;
    }

    String peek() {
        if (isEmptyQuiet()) {
            System.out.println("peek -> stack is empty");
            return null;
        }
        System.out.println("peek -> " + top.data);
        return top.data;
    }

    boolean isEmpty() {
        System.out.println("isEmpty -> " + (top == null));
        return top == null;
    }

    private boolean isEmptyQuiet() {
        return top == null;
    }

    public static void main(String[] args) {
        Problem5 s = new Problem5();
        s.push("P1");
        s.push("P2");
        s.peek();
        s.pop();
        s.push("P3");
        s.pop();
        s.pop();
        s.pop();
        s.isEmpty();
    }
}
