public class LinkedStack {
    private Node top = null;

    private class Node {
        int value;
        Node next;
    }

    public void push(int item) {
        Node next = top;
        top = new Node();
        top.value = item;
        top.next = next;
    }

    public int pop() {
        int item = top.value;
        top = top.next;
        return item;
    }

    public boolean isEmpty() {
        return top == null
    }
}
