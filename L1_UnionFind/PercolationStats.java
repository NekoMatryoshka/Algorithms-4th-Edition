import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private Percolation percolation;
    private double[] results;

    public PercolationStats(int n, int trials) {

        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            int openCount = 0;
            while(!percolation.percolates()) {
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomCol = StdRandom.uniform(1, n + 1);
                if(!percolation.isOpen(randomRow, randomCol)) {
                    percolation.open(randomRow, randomCol);
                    openCount++;
                }
            }
            results[i] = (double)openCount / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        double duration = watch.elapsedTime();

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        StdOut.println("calculation duration    = " + duration + "s");
    }
}
//javac -cp .:../algs4/algs4.jar PercolationStats.java
//java -cp .:../algs4/algs4.jar PercolationStats 200 100

/**
 * when T = 100, N = 100 -> 0.674s, N = 200 -> 2.721s, N = 400 -> 16.334s
 * when N = 100, T = 100 -> 0.674s, T = 200 -> 1.299s, T = 400 -> 2.523s
 * it is clear that time to N is linear, but time to T is quadratic (use wolframaplpha "curve fitting (100, 0.674), (200, 2.721), (400, 16.334)" to validate)
 **/
