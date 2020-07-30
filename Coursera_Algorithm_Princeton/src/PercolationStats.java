import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;


/*16 bytes (object overhead)
 *8 bytes reference to double array
 *8N + 24 bytes (double[] array)
 *4 bytes padding
 *Total 8N + 52 bytes
*/

import java.lang.Math;

public class PercolationStats {
	
	//int n;
	//int trials;
	
	private double[] results; 
	
	// perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if(n <= 0 || trials <= 0)
    		throw new IllegalArgumentException("Invalid inputs. [Re]enter the inputs.");
    	
    	results = new double[trials];
    	  	
    	int t = 0;
    	
    	while(t < trials) {
    		
    		Percolation p1 = new Percolation(n);
    		
    		while(!p1.percolates()) // while the system does not percolate
        	{
        		//Choose a site uniformly at random among all blocked sites.
        		int row = StdRandom.uniform(1, n + 1);
        		int col = StdRandom.uniform(1, n + 1);
        		
        		//Open the site if it is blocked.
        		if(!p1.isOpen(row, col))
        			p1.open(row, col);
        		
        	}
    		
    		results[t] = (double) p1.numberOfOpenSites()/(n*n);
    		
    		
    		//System.out.println("n= " + n + " trial = " + t + " percolation threshold = " + results[t]);
    				
    		t++;
    		
    	}
    	
    	
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	if(results.length == 1)
    		return Double.NaN;
    	return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return this.mean() - (1.96 * this.stddev()/Math.sqrt((double)results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return this.mean() + (1.96 * this.stddev()/Math.sqrt((double)results.length));
    }
    
    // test client (see below)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		Stopwatch timer1 = new Stopwatch();
		
		PercolationStats mySimulation = new PercolationStats(n, T);
		
		System.out.println("mean = "   + mySimulation.mean());
		System.out.println("stddev = " + mySimulation.stddev());
		System.out.println("95% confidencene interval = [" + mySimulation.confidenceLo()
		                   + ", " + mySimulation.confidenceHi() + "]");
		
		double time1 = timer1.elapsedTime();
		
		StdOut.printf("time = " + "(%.2f seconds)\n", time1);

		
		
	}

}
