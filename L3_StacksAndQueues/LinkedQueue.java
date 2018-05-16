public class LinkedQueue {
    private Node top, bot;

    private class Node {
        int value;
        Node next;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void enqueue(int item) {
        Node oldBot = bot;
        bot = new Node();
        bot.value = item;
        bot.next = null;

        if (isEmpty())
            top = bot;
        else
            oldBot.next = bot;
    }

    public int dequeue() {
        int item = top.value;
        top = top.next;
        if (isEmpty())
            bot = null;
        return item;
    }
}
