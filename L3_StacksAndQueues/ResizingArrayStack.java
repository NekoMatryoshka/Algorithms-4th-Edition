public class ResizingArrayStack {
    private int[] items = new int[10];
    private int top = 0;

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(int item) {
        items[top++] = item;
        if (top == items.length)
            resize(items.length * 2);
    }

    public int pop() {
        int item = items[--top];
        items[top] = null;
        if(top > 0 && top == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    private void resize(int capacity) {
        int[] newArray = new int[capacity];
        for (int i = 0; i < top; i++)
            newArray[i] = items[i];
        items = newArray;
    }
}
