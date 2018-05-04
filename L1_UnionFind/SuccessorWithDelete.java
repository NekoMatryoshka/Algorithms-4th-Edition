public class SuccessorWithDelete {  //implement with an array-formed double linked list
    private int[] successor;  //successor is the smallest larger node in the array
    private int[] predecessor;  //predecessor is the reverse of successor

    public SuccessorWithDelete(int N) {
        successor = new int[N];
        predecessor = new int[N];

        for(int i = 0; i < N; i++) {
            successor[i] = i + 1;  //because S is linear, the successor is always one number larger
            predecessor[i] = i - 1;
        }
    }

    public int getSuccessor(int i) {
        return  successor[i];
    }

    public SuccessorWithDelete remove(int i) {
        successor[predecessor[i]] = successor[i];
        predecessor[successor[i]] = predecessor[i];  //remove the relationship of i in two arrays
        successor[i] = -1;
        predecessor[i] = -1;  //destroy the data, so that query of i will return abnormal number -1
        return this;
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);
        swd.remove(1).remove(2).remove(3).remove(8);
        System.out.println("0:" + swd.getSuccessor(0));
        System.out.println("1:" + swd.getSuccessor(1));
        System.out.println("4:" + swd.getSuccessor(4));
        System.out.println("7:" + swd.getSuccessor(7));
    }
}
