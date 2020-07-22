/**
 * 
 */

/**
 * @author Long Dang
 *
 */

import java.util.*;

public class Percolation {
	
	int[][] mySys;
	private int N;
	private int[] id;
	private int[] sz; // count number of objects in the tree rooted at vertex i_th
	
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	
    	if (n <= 0) {
    		throw new IllegalArgumentException("The input n must be > 0.");
    	}
    	
    	N = n;
    	//By convention, the row and column indices are integers between 1 and n, 
    	//where (1, 1) is the upper-left site:
    	mySys = new int[N + 1][N + 1];
    	
    	id = new int[(N + 1)*(N + 1)];
		sz = new int[(N + 1)*(N + 1)];
		
		
		
    	for(int i = 1; i <= N; i++) {
    		for(int j = 1; j <= N; j++) {
    			mySys[i][j] = -1; // all sites are blocked
    			id[xyTo1D(i, j)] = xyTo1D(i, j);
    			sz[xyTo1D(i, j)] = 1;
    		}
    	}
		
	
    	
    }
    
    private int xyTo1D(int row, int col) {
    	
    	return (row - 1) * N + col;
    	
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return 0;
    }

    // does the system percolate?
    public boolean percolates() {
    	return false;
    }

    // test client (optional)
    public static void main(String[] args) {
    	
    	
    	//test the Percolation() constructor
    	Percolation p1 = new Percolation(5);
    	
    	//test int xyTo1D(int, int)
    	System.out.println(p1.xyTo1D(1, 1));
    	
    	System.out.println(p1.xyTo1D(2, 5));
    	
    	System.out.println(p1.xyTo1D(3, 3));
    	
    	//test printing out the id array
    	for(int i = 1; i <= p1.N; i++) {
    		for(int j = 1; j <= p1.N; j++) {
    			System.out.print(p1.id[p1.xyTo1D(i, j)] + " ");
    		}
    		System.out.println();
    	}
    		
    }

}
