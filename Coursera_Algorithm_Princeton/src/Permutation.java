import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//duplicate? peek?
		RandomizedQueue<String> randQ = new RandomizedQueue<String>();
		
		//String[]
		
		//int k = 3;
		int k = Integer.parseInt(args[0]);
		
		//RandomizedQueue object of maximum size at most k
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			
			randQ.enqueue(item);
		}
		
		//Knuth shuffling algorithm produces a
		//uniformly random permutation of the input array in linear time
		
//		for(String s: randQ) {
//			if(k > 0) {
//				//StdOut.println(k);
//				StdOut.println(s);
//				k--;
//			}
//					
//		}
		
		for(int i = 0; i < k; i++)
			StdOut.println(randQ.dequeue());
		
		
	}

}
