//try to memorize the final optimized algorithm after learning

public class WeightedQuickUnionWithPathCompression {
    private int[] father;
    private int[] size;  //size of the tree
    private int[] maxNode; //largest node of the tree
    private int componentCount;  //number of connectivity component in the network

    public WeightedQuickUnionWithPathCompression(int N) {
        father = new int[N];
        size = new int[N];
        maxNode = new int[N];
        componentCount = N;

        for(int i = 0; i < N; i++) {
            father[i] = i;
            size[i] = 1;
            maxNode[i] = i;
        }
    }

    public boolean isFullyConnected() {
        return componentCount == 1;
    }

    public int getLowestStepCountBeforeFullyConnected() {  //interview problem 1: get lowest step count until fully connected
        return componentCount - 1;
    }

    private int getRoot(int i) {  //max child does not change because path compression does not change the connectivity
        while(i != father[i]) {
            father[i] = father[father[i]];  //path compression, optimization per loop to flat the tree
            i = father[i];
        }
        return i;
    }

    public int find(int i) {  //interview problem 2: find the largest node connected to i
        return maxNode[getRoot(i)];
    }

    public boolean isConnected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);

        if(pRoot == qRoot)
            return;

        componentCount --; //if pRoot != qRoot, p and q is not connected, then union them reduce component count by 1

        if(size[pRoot] > size[qRoot]) {  //weighting the size of tree to always add smaller tree into larger tree, also known as balanced tree
            father[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
            maxNode[pRoot] = maxNode[pRoot] > maxNode[qRoot] ? maxNode[pRoot] : maxNode[qRoot];
        } else {
            father[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
            maxNode[qRoot] = maxNode[pRoot] > maxNode[qRoot] ? maxNode[pRoot] : maxNode[qRoot];
        }
    }

    public static void main(String[] args) {  //test function
        WeightedQuickUnionWithPathCompression qu = new WeightedQuickUnionWithPathCompression(10);
        qu.union(1, 2); qu.union(1, 5); qu.union(1, 3); qu.union(3, 4); qu.union(2, 7);
        qu.union(0, 6); qu.union(0, 8);
        System.out.println("4, 7: " + qu.isConnected(4, 7));
        System.out.println("8, 6: " + qu.isConnected(8, 6));
        System.out.println("0, 7: " + qu.isConnected(0, 7));
        System.out.println("4, 9: " + qu.isConnected(4, 9));
        System.out.println("4 largest: " + qu.find(4));
        System.out.println("8 largest: " + qu.find(8));
        System.out.println("9 largest: " + qu.find(9));
        System.out.println("FullyConnected: " + qu.isFullyConnected() + ", Steps: " + qu.getLowestStepCountBeforeFullyConnected());
    }
}
