/**
 * 
 */

/**
 * @author Long Dang
 *
 */

import java.util.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	
	int[][] mySys; // tells that sites are open, blocked
	private int N;
	//private int[] id; // for union�find data structure, tells that which sites are connected to which other sites
	//private int[] sz; // for union�find data structure, count number of objects in the tree rooted at vertex i_th
	private WeightedQuickUnionUF mySetArray;
	private int opened;
	
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	
    	if (n <= 0) {
    		throw new IllegalArgumentException("The input n must be > 0.");
    	}
    	
    	N = n;
    	
    	opened = 0;
    	
    	//By convention, the row and column indices are integers between 1 and n, 
    	//where (1, 1) is the upper-left site:
    	mySys = new int[N + 1][N + 1];
    	
    	//id = new int[(N + 1)*(N + 1)];
		//sz = new int[(N + 1)*(N + 1)];
    	
    	mySetArray = new WeightedQuickUnionUF((N + 1)*(N + 1));
		
		
		
    	for(int i = 1; i <= N; i++) {
    		for(int j = 1; j <= N; j++) {
    			mySys[i][j] = -1; // all sites are blocked
    			//id[xyTo1D(i, j)] = xyTo1D(i, j);
    			//sz[xyTo1D(i, j)] = 1;
    		}
    	}
		
	
    	
    }
    
    private boolean connected(int p, int q) {
		int i = mySetArray.find(p);
		int j = mySetArray.find(q);
		return i == j;
	}
//    
//    private int twoPassRoot(int node_i_index) {
//		ArrayList<Integer> visited = new ArrayList<Integer>();
//		while(node_i_index != this.id[node_i_index]) // when the given node is not the root node 
//		{	
//			visited.add(node_i_index);
//			node_i_index = id[node_i_index]; //chase parent pointers until reach root
//			//visited.add(node_i_index);
//		}
//		// for all visited node, set its id to point to the root node
//		for(Integer v: visited) {
//			//if(id[v] != node_i_index)
//				id[v] = node_i_index;
//				
//		}
//		return node_i_index;
//	}
//    
//    private void union(int p, int q)
//	{
//		int p_root_id = twoPassRoot(p); // takes at most logV
//		int q_root_id = twoPassRoot(q);
//		
//		if(p_root_id == q_root_id) return;
//		
//		if(this.sz[p_root_id] < this.sz[q_root_id]) {
//			id[p_root_id] = q_root_id;
//			sz[q_root_id] += sz[p_root_id];
//		}
//		else {
//			id[q_root_id] = p_root_id;
//			sz[p_root_id] += sz[q_root_id];
//		}
//	}
    
    private int xyTo1D(int row, int col) {
    	
    	return (row - 1) * N + col;
    	
    }
    
    //the row and column indices must be between 1 and N
    private boolean isInvalidIndex(int index) {
    	return (index <= 0 || index > N);
    	
    }
    
//    private boolean isInvalidIndex(int row, int col) {
//    	
//    	return (index <= 0 || index > N);
//    	
//    }

    // opens the site (row, col) if it is not open already
    // WeightedQuickUnionUF - Union method
    // 1 means open
    public void open(int row, int col) {
    	//validate the indices of the site that it receives
    	if(isInvalidIndex(row) || isInvalidIndex(col))
    		throw new IllegalArgumentException("row/column index i out of bounds");
    	
    	if(!isOpen(row, col)) {
    		//mark the site as open
    		this.mySys[row][col] = 1;
    		opened++;

    		//perform some sequence of WeightedQuickUnionUF operations 
    		//that links the site [row, col] to its open neighbors [left, right, up, down] open sites.
    		//left neighbor
    		if(isOpen(row, col - 1) && !connected(xyTo1D(row, col), xyTo1D(row, col - 1))) {
    			mySetArray.union(xyTo1D(row, col), xyTo1D(row, col - 1));
    		}
    		//right neighbor
    		if(isOpen(row, col + 1) && !connected(xyTo1D(row, col), xyTo1D(row, col + 1)))
    			mySetArray.union(xyTo1D(row, col), xyTo1D(row, col + 1));

    		//up neighbor
    		if(isOpen(row - 1, col) && !connected(xyTo1D(row, col), xyTo1D(row - 1, col)))
    			mySetArray.union(xyTo1D(row, col), xyTo1D(row - 1, col));

    		//down neighbor
    		if(isOpen(row + 1, col) && !connected(xyTo1D(row, col), xyTo1D(row + 1, col)))
    			mySetArray.union(xyTo1D(row, col), xyTo1D(row + 1, col));
    		
    	}
    	
    		
    		
    	 
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	if(isInvalidIndex(row) || isInvalidIndex(col))
    		throw new IllegalArgumentException("row/column index i out of bounds");
    	return mySys[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if(isInvalidIndex(row) || isInvalidIndex(col))
    		throw new IllegalArgumentException("row/column index i out of bounds");
    	return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return opened;
    }

    // does the system percolate?
    // WeightedQuickUnionUF - Connected method
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
    			System.out.print(p1.mySetArray.find(p1.xyTo1D(i, j)) + " ");
    		}
    		System.out.println();
    		
    	}
    	
    	System.out.println(p1.isInvalidIndex(-1));
    	System.out.println(p1.isInvalidIndex(5));
    	//test connected
    	System.out.println("test connected");
    	p1.open(2, 4);
    	
    	p1.connected(p1.xyTo1D(2, 3), p1.xyTo1D(2, 4));
    	
    	System.out.println("open (2, 4) ");
    	for(int i = 1; i <= p1.N; i++) {
    		for(int j = 1; j <= p1.N; j++) {
    			System.out.print(p1.mySetArray.find(p1.xyTo1D(i, j)) + " ");
    		}
    		System.out.println();
    		
    	}
    	
    	p1.open(2, 3);
    	
    	System.out.println("open (2, 3) ");
    	for(int i = 1; i <= p1.N; i++) {
    		for(int j = 1; j <= p1.N; j++) {
    			System.out.print(p1.mySetArray.find(p1.xyTo1D(i, j)) + " ");
    		}
    		System.out.println();
    		
    	}
    	
    	System.out.println(p1.connected(p1.xyTo1D(2, 3), p1.xyTo1D(2, 4)));
    	
    	p1.open(3, 4);
    	System.out.println(p1.connected(p1.xyTo1D(3, 4), p1.xyTo1D(2, 4)));
    	
    	//p1.open(2, 5);
    	//System.out.println(p1.connected(p1.xyTo1D(2, 5), p1.xyTo1D(2, 4)));
    	
    	System.out.println(p1.connected(p1.xyTo1D(2, 3), p1.xyTo1D(3, 4)));
    	
    	System.out.println("open (3, 4) ");
    	for(int i = 1; i <= p1.N; i++) {
    		for(int j = 1; j <= p1.N; j++) {
    			System.out.print(p1.mySetArray.find(p1.xyTo1D(i, j)) + " ");
    		}
    		System.out.println();
    		
    	}
    	System.out.println("number of open sites = " + p1.opened);
    }

}
