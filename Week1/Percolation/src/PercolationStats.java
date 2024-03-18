import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private int experiments;
    private double[] fractionOfOpenSites;
    private double confidenceConst = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        experiments = trials;
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Insert positive non zero integers.");
        }
        fractionOfOpenSites = new double[experiments];
        for (int i = 0; i < experiments; i++) {
            Percolation pc = new Percolation(n);
            while (!pc.percolates()) {
                int x = StdRandom.uniformInt(1, n + 1);
                int y = StdRandom.uniformInt(1, n + 1);
                if (!pc.isOpen(x, y) && !pc.isFull(x, y)) {
                    pc.open(x, y);
                }
            }
            int countofOpen = pc.numberOfOpenSites();
            fractionOfOpenSites[i] = (double) countofOpen / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractionOfOpenSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractionOfOpenSites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (confidenceConst * stddev() / Math.sqrt(experiments));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (confidenceConst * stddev() / Math.sqrt(experiments));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Missing arguments.");
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        String confidence = ("[" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
        StdOut.println("mean                    =" + ps.mean());
        StdOut.println("stddev                  =" + ps.stddev());
        StdOut.println("95% confidence interval =" + confidence);

    }

}
