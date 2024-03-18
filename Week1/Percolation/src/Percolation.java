import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF ufT;
    private WeightedQuickUnionUF ufTB;
    private int gridSize;
    private int countofOpen;
    private int top;
    private int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size should be positive integer. (N > 0)");
        }
        gridSize = n;
        grid = new boolean[gridSize][gridSize];
        ufTB = new WeightedQuickUnionUF(gridSize * gridSize + 2); // +2 is for virtual top, bottom sites
        ufT = new WeightedQuickUnionUF(gridSize * gridSize + 1); // +1 is for virtual top site
        countofOpen = 0;
        top = gridSize * gridSize;
        bottom = top + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            // open site
            grid[row - 1][col - 1] = true;
            countofOpen++;

            // connect site to virtual top site
            if (row == 1) {
                ufTB.union(index(row, col), top);
                ufT.union(index(row, col), top);
            }
            // connect site to virtual bottom site
            if (row == gridSize) {
                ufTB.union(index(row, col), bottom);
            }
            // connect the rest of the neighbor open sites
            if (row > 1 && isOpen(row - 1, col)) {
                ufTB.union(index(row, col), index(row - 1, col));
                ufT.union(index(row, col), index(row - 1, col));
            }
            if (row < gridSize && isOpen(row + 1, col)) {
                ufTB.union(index(row, col), index(row + 1, col));
                ufT.union(index(row, col), index(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                ufTB.union(index(row, col), index(row, col - 1));
                ufT.union(index(row, col), index(row, col - 1));
            }
            if (col < gridSize && isOpen(row, col + 1)) {
                ufTB.union(index(row, col), index(row, col + 1));
                ufT.union(index(row, col), index(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufT.find(index(row, col)) == ufT.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countofOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufTB.find(bottom) == ufTB.find(top);
    }

    // test client (optional)
    public static void main(String[] args) {
    }

    private int index(int row, int col) {
        return (row - 1) * gridSize + col - 1;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException("Site out of boundaries.");
    }
}
