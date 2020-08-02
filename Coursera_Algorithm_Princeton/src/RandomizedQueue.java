import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] q;
	
	private int head, tail, N;
	
	// construct an empty randomized queue
    public RandomizedQueue() {
    	head = 0; 
    	tail = 0; // tail = N, the position for the next item to appear, tail++
    	N = 0;
    	q = (Item[]) new Object[1];
    	assert check();
    	
    }
    
    public RandomizedQueue(int capacity) {
    	head = 0; 
    	tail = 0; // tail = N, the position for the next item to appear, tail++
    	N = 0;
    	q = (Item[]) new Object[capacity];
    	assert check();
    	
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return N;
    }

    // add the new item at q[tail]
    public void enqueue(Item item) {
    	if(N == q.length) resize(2*q.length);
    	
    	if(head != 0 && tail % q.length == 0) //if the array is not full and get past the capacity, next: test this by continue enqueue and read more about clock arithmetic http://mathandmultimedia.com/2012/07/08/clock-arithmetic/#:~:text=The%20kind%20of%20number%20system,modulus%20is%20equal%20to%2012.
    		updateQArray();
    	
    	q[tail++] = item;
    	N++;
    	
    }

    // remove and return a random item, but maintain head pointer
    public Item dequeue() {
    	Item item = q[head];
    	q[head++] = null; //avoid loitering
    	N--;
    	return item;
    }
    

    // return a random item (but do not remove it)
    public Item sample() {
    	return null;
    }

    @Override
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
    
	private  void printRandomQueue() {
   		//StringBuilder s = new StringBuilder();
		int i = 0;
   		String result = " ";
   		while(i < q.length) {
//   			if(q[i] != null)
//   				result += q[i] + " ";
   			
   			result += q[i] + " - ";
   			i++;
   		}
   		StdOut.println(result);
   	}
    
    // precondition: when get past the capacity, reset head to zero, and update tail
    // Is it run infrequently?
    private void updateQArray() {
    	for(int i = head; i < tail; i++) {
    		// i + N >= capacity
    		q[(i + N) % q.length] = q[i];
    	}
    	for(int i = N; i < q.length; i++) {
    		if(q[i] != null)
    			q[i] = null;
    	}
    	
    	head = 0;
    	tail = N;
    }
    
    private void resize(int capacity) {
    	
    	Item[] copy = (Item[]) new Object[capacity];
    	
    	for(int i = head; i < tail; i++)
    		copy[i] = q[i];
    	
    	q = copy;
  
    }

 	//check internal invariants
   	private boolean check() {
   		if(N < 0) {
   			return false;
   		}
//   		if(N == 0) {
//   			if(q != null) return false;
//   		}
//   		else if(N == 1) {
//   			if(first == null || last == null) return false;
//   			if(first.next != null || last.prev != null) return false;
//   			//How about the tail check the Queue sample program
//   		}
//   		else {
//   			if (first == null) return false;
//   			if(first.next == null || last.prev == null) return false;
//   		}
   		return true;
   	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> randQ = new RandomizedQueue<String>();
		
		//***********************************
		
		StdOut.println("Call enqueue, then dequeue repeatedly");
		
		randQ.enqueue("to");
		
		randQ.enqueue("be");
		
		randQ.enqueue("or");
		
		randQ.enqueue("not");
		
		randQ.enqueue("to");
		
		randQ.dequeue();
		
		randQ.enqueue("be");
		
		randQ.dequeue();
		
		randQ.dequeue();
		
		randQ.enqueue("that");
		
		randQ.dequeue();
		
		randQ.dequeue();
		
		randQ.dequeue();
		
		randQ.enqueue("is");
		
		randQ.enqueue("worst");
		
		StdOut.println("(" + randQ.size() + " left on deque)");
		
		randQ.printRandomQueue();
		
//		String result = " ";
//		
//		int origSize = randQ.size();
//		
//		StdOut.println("Call removeLast " + origSize + " consecutive times: " + result);
//		
//			
//		for(int i = 0; i < origSize; i++) {
//			
//			result += randQ.removeLast() + " ";
//			
//			//StdOut.println("Remove items in this order: " + result);
//			
//		}
//		
//		StdOut.println("Remove items in this order: " + result);
//		StdOut.println("(" + randQ.size() + " left on deque)");

	}

	
}
