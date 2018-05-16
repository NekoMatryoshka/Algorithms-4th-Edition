public class ArrayStack {
    private int[] items;
    private int top = 0;

    public ArrayStack(int capacity) {
        items = new int[capacity];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(int item) {
        items[top++] = item;
    }

    public int pop(){
        return items[--top];
    }
}
