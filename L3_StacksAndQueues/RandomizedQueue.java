import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            newArray[i] = items[i];
        items = newArray;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == items.length)
            resize(2 * items.length);
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int random = StdRandom.uniform(size);
        Item item = items[random];
        items[random] = items[--size];
        items[size] = null;
        if (size > 0 && size == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return items[StdRandom.uniform(size)];
    }

    public java.util.Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements java.util.Iterator<Item> {
        private int current = 0;
        private int[] randomOrder;

        public RandomizedQueueIterator() {
            randomOrder = new int[size];
            for (int i = 0; i < size; i++)
                randomOrder[i] = i;
            StdRandom.shuffle(randomOrder);
        }

        public boolean hasNext() {
            return current < size;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return items[randomOrder[current++]];
         }
    }
}