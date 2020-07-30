/**
 * 
 */

/**
 * @author Long Dang
 *
 */


import edu.princeton.cs.algs4.WeightedQuickUnionUF; // https://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html


/* 16 bytes (object overhead)
 * N*N bytes for the 2 dimensional boolean array
 * 4 bytes for N
 * ~8N bytes for WeightedQuickUnionUF object
 * 4 bytes for opened
 * Approximate total ~N*N bytes
*/

public class Percolation {
	
	private boolean[][] mySys; // tells that sites are open, blocked
	private int N;
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
    	mySys = new boolean[N + 1][N + 1];
    	
    	//id = new int[(N + 1)*(N + 1)];
		//sz = new int[(N + 1)*(N + 1)];
    	
    	mySetArray = new WeightedQuickUnionUF((N)*(N) + 2);
		
		
		
    	for(int i = 1; i <= N; i++) {
    		for(int j = 1; j <= N; j++) {
    			mySys[i][j] = false; // all sites are blocked
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
    
 // returns the number of open sites
//    private int numberOfAdjacenctSites(int row, int col) {
//    	if((row == 1 && col == 1) ||
//    	   (row == N && col == 1) ||
//    	   (row == 1 && col == N) ||
//    	   (row == N && col == N)) {
//    		return 3;
//    	}
//    	return 4;
//    }
    
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
    private void unionSites(int row, int col, int nrow, int ncol) {
    	// already open and not connected, then union
    	if(isOpen(nrow, ncol) && !connected(xyTo1D(row, col), xyTo1D(nrow, ncol)))
			mySetArray.union(xyTo1D(row, col), xyTo1D(nrow, ncol));
    }
   
    //Precondition: coordination is already valid
    private void openSurroundingNeigbors(int row, int col) {
    	openLeftNeighbors(row, col, row, col - 1);
    	openAboveNeighbors(row, col, row - 1, col);
    	openRightNeighbors(row, col, row, col + 1);
    	openBelowNeighbors(row, col, row + 1, col);
    	
    }
    
    private void openLeftNeighbors(int row, int col, int nrow, int ncol) {
    	if(col != 1)//not left most col
    	{
    		unionSites(row, col, nrow, ncol);
    	}
    	//left most col, ignore it
    }
    private void openAboveNeighbors(int row, int col, int nrow, int ncol) {
    	if(row == 1) //top row 
    	{
    		mySetArray.union(xyTo1D(row, col), mySetArray.find(0)); // union with top virtual site
    	}
    	else {
    		unionSites(row, col, nrow, ncol);
    	}
    	
    }
    
    private void openRightNeighbors(int row, int col, int nrow, int ncol) {
    	if(col != N)//not left most col
    	{
    		unionSites(row, col, nrow, ncol);
    	}
    	//right most col, ignore it
    }
    
    private void openBelowNeighbors(int row, int col, int nrow, int ncol) {
    	if(row == N) //bottom row 
    	{
    		mySetArray.union(xyTo1D(row, col), mySetArray.find(N*N + 1)); // union with bottom virtual site
    	}
    	else {
    		unionSites(row, col, nrow, ncol);
    	}
    }
    
    // opens the site (row, col) if it is not open already
    // WeightedQuickUnionUF - Union method
    // 1 means open
    public void open(int row, int col) {
    	//validate the indices of the site that it receives
    	if(isInvalidIndex(row) || isInvalidIndex(col))
    		throw new IllegalArgumentException("row/column index i out of bounds");
    	
    	if(!isOpen(row, col)) {
    		//mark the site as open
    		this.mySys[row][col] = true;
    		opened++;
    		//links the site to its open neighbors
    		// site (1,1) and site (1, N)
    		
    		openSurroundingNeigbors(row, col);
    		
    		
    	}
    	
    		
    		
    	 
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	if(isInvalidIndex(row) || isInvalidIndex(col))
    		throw new IllegalArgumentException("row/column index i out of bounds");
    	return mySys[row][col] == true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if(isInvalidIndex(row) || isInvalidIndex(col))
    		throw new IllegalArgumentException("row/column index i out of bounds");
    	
    	int i = mySetArray.find(xyTo1D(row, col));
		int j = mySetArray.find(0); // top virtual site
		return i == j;
    	
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return opened;
    }

    // does the system percolate?
    // WeightedQuickUnionUF - Connected method
    public boolean percolates() {
    	
    	int i = mySetArray.find(N*N + 1); // bottom virtual site
		int j = mySetArray.find(0); // top virtual site
		
		return i == j;
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
    	//left of (2, 4)
    	p1.open(2, 3);
    	
    	System.out.println("open (2, 3) ");
    	for(int i = 1; i <= p1.N; i++) {
    		for(int j = 1; j <= p1.N; j++) {
    			System.out.print(p1.mySetArray.find(p1.xyTo1D(i, j)) + " ");
    		}
    		System.out.println();
    		
    	}
    	
    	System.out.println(p1.connected(p1.xyTo1D(2, 3), p1.xyTo1D(2, 4)));
    	//below of (2 ,4)
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
    	//******************************************************************//
    	//test open based on the lecture's slides
    	//test the Percolation() constructor
    	
    	System.out.println("test open() method");
    	p1 = new Percolation(5);
    	p1.open(1, 1);
    	System.out.println("open (1, 1) "); 	
    	p1.open(1, 2);
    	System.out.println("open (1, 2) "); 	
    	p1.open(1, 4);
    	System.out.println("open (4, 1) ");
    	p1.open(2, 4);
    	System.out.println("open (2, 4) ");
    	p1.open(3, 4);
    	System.out.println("open (3, 4) ");
    	p1.open(3, 5);
    	System.out.println("open (3, 5) ");
    	p1.open(4, 1);
    	System.out.println("open (4, 1) ");
    	p1.open(5, 1);
    	System.out.println("open (5, 1) ");
    	p1.open(5, 2);
    	System.out.println("open (5, 2) ");
    	p1.open(5, 4);
    	System.out.println("open (5, 4) ");
    	p1.open(5, 5);
    	System.out.println("open (5, 4) ");
    	
    	for(int i = 1; i <= p1.N; i++) {
    		for(int j = 1; j <= p1.N; j++) {
    			System.out.print(p1.mySetArray.find(p1.xyTo1D(i, j)) + " ");
    		}
    		System.out.println();
    		
    	}
    	System.out.println("number of open sites = " + p1.opened);
    	//System.out.println("number of sets  = " + p1.mySetArray.count());
    }

}
