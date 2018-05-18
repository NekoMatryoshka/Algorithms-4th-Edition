public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head, tail;
    private int size = 0;

    private class Node<Item> {
        Item value;
        Node<Item> previous;
        Node<Item> next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> oldHead = head;
        head = new Node<>();
        head.value = item;
        head.previous = null;
        head.next = oldHead;
        if (tail == null)
            tail = head;
        else
            oldHead.previous = head;
        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> oldTail = tail;
        tail = new Node<>();
        tail.value = item;
        tail.previous = oldTail;
        tail.next = null;
        if (head == null)
            head = tail;
        else
            oldTail.next = tail;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = head.value;
        head = head.next;
        if (head == null)
            tail = null;
        else
            head.previous = null;
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = tail.value;
        tail = tail.previous;
        if (tail == null)
            head = null;
        else
            tail.next = null;
        size--;
        return item;
    }

    public java.util.Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements java.util.Iterator<Item> {
        Node<Item> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.value;
            current = current.next;
            return item;
        }
    }
}