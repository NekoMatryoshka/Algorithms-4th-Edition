import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLength;
    private WeightedQuickUnionUF UF;
    private int [][] grids;
    private int top = 0;
    private int bot;
    private int openSiteCount = 0;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        UF = new WeightedQuickUnionUF(n * n + 2);
        sideLength = n;
        bot = n * n + 1;
        grids = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                grids[i][j] = 0;
            }
        }
    }

    private int getIndex(int row, int col) {  //convert (col, row) into array index
        return (row - 1) * sideLength + (col - 1) + 1;
    }

    private void validate(int row, int col) {
        if((row > sideLength) || (col > sideLength)) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) throws IllegalArgumentException {
        validate(row, col);
        int index = getIndex(row, col);
        if (isOpen(row, col))
            return;
        grids[row-1][col-1] = 1;
        openSiteCount ++;

        if(row != sideLength && isOpen(row + 1, col))
            UF.union(index, getIndex(row + 1, col));
        if(row != 1 && isOpen(row - 1, col))
            UF.union(index, getIndex(row - 1, col));
        if(col != sideLength && isOpen(row, col + 1))
            UF.union(index, getIndex(row, col + 1));
        if(col != 1 && isOpen(row, col - 1))
            UF.union(index, getIndex(row, col - 1));
        if(row == 1)
            UF.union(index, top);
        if(row == sideLength)
            UF.union(index, bot);
    }

    public boolean isOpen(int row, int col) throws IllegalArgumentException {
        validate(row, col);
        return grids[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) throws IllegalArgumentException {
        validate(row, col);
        return UF.connected(getIndex(row, col), top);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return UF.connected(top, bot);
    }
}

//javac -cp ../algs4/algs4.jar Percolation.java
