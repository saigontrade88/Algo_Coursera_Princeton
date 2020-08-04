import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.prism.sw.SWPipeline;

import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdOut;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] q; //array of items
	
	private int head, tail, N;
	
	private int randPos; 
	
	// construct an empty randomized queue
    public RandomizedQueue() {
    	head = 0; 
    	tail = 0; // tail = N, the position for the next item to appear, tail++
    	N = 0;
    	q = (Item[]) new Object[2];
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
    	if(item == null) throw new IllegalArgumentException();
    	
    	//double size of array if necessary
    	if(N == q.length) resize(2*q.length); //double size of array if necessary
    	
//    	if(head != 0 && tail % q.length == 0) //if the array is not full and get past the capacity, next: test this by continue enqueue and read more about clock arithmetic http://mathandmultimedia.com/2012/07/08/clock-arithmetic/#:~:text=The%20kind%20of%20number%20system,modulus%20is%20equal%20to%2012.
//    		updateQArray();
    	
    	q[tail++] = item; //add item
    	if (tail == q.length) tail = 0; // since we remove item from the front, the front always has open space. Wrap-around.
    	N++;
    	
    }

    // remove and return a random item, but maintain head pointer
    public Item dequeue() {
    	
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    	
    	//Item item = q[head];
    	
    	Item item = sample(); //return a random item
    	
    	Swap(q, head, randPos);
    	
    	StdOut.println("Call to produce a random element at pos = " + getRandPos());
    	
    	q[head++] = null; //avoid loitering and update head position
    	
    	N--;
    	
    	if(head == q.length) head = 0; //the least recently used item is the first item, the next item to remove is at 0 position; wrap around
    	// shrink size of array if necessary
    	if (N > 0 && N == q.length/4) resize(q.length/2);
    	
    	randPos = -1;
    	
    	return item;
    }
    
    private void Swap(Item[] a, int i, int j) {
    	
    	Item temp = a[j];
    	
    	a[j] = a[i];
    	
    	a[i] = temp;
    	
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    	
    	//Choose an item uniformly at random among a.
		randPos = StdRandom.uniform(N);
		
		//locate randPos in the q
		randPos = (head + randPos) % q.length;
		
    	return q[randPos];
    }

    @Override
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ArrayIterator();
	}
    
    private class ArrayIterator implements Iterator<Item> {
    	private int i = 0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i < N;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if(!hasNext()) throw new NoSuchElementException();
			
			//StdRandom.shuffle(q);
			
			Item item = q[(head + i) % q.length];
			i++;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
    	
    }
    
    
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
   		for(Item item: this)
   			s.append(item + " ");
		return s.toString();
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
//    private void updateQArray() {
//    	for(int i = head; i < tail; i++) {
//    		// i + N >= capacity
//    		q[(i + N) % q.length] = q[i];
//    	}
//    	for(int i = N; i < q.length; i++) {
//    		if(q[i] != null)
//    			q[i] = null;
//    	}
//    	
//    	head = 0;
//    	tail = N;
//    }
    
    /**
	 * @return the randPos
	 */
	private int getRandPos() {
		return randPos;
	}

	/**
	 * @param randPos the randPos to set
	 */
	private void setRandPos(int randPos) {
		this.randPos = randPos;
	}

	private void resize(int capacity) {
    	
    	assert capacity >= N;
    	
    	Item[] copy = (Item[]) new Object[capacity];
    	
    	for(int i = 0; i < N; i++)
    		copy[i] = q[((head + i) % q.length)]; //like a clock, starting at head position, if (head + i) get past q.length, reset it
    	
    	q = copy;
    	head = 0; //reset it to 0, the beginning and the least recently used
    	tail = N; // the next item position to be added
  
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
		
		//StdOut.println("First call to produce a random element: " + randQ.sample().toString() + " pos = " + randQ.getRandPos());
		
		randQ.enqueue("not");
		
		randQ.enqueue("to");
				
		//StdOut.println("Second call to produce a random element: " +randQ.sample().toString()+ " pos = " + randQ.getRandPos());
		
		StdOut.println(randQ.dequeue() + " ");
		
		randQ.enqueue("be");
		
		//StdOut.println("Third call to produce a random element: " +randQ.sample().toString()+ " pos = " + randQ.getRandPos());
				
		StdOut.println(randQ.dequeue() + " ");
			
		StdOut.println(randQ.dequeue() + " ");
		
		//StdOut.println("Fourth call to produce a random element: " + randQ.sample().toString()+ " pos = " + randQ.getRandPos());
		
		randQ.enqueue("that");
			
		StdOut.print(randQ.dequeue() + " ");
				
		StdOut.print(randQ.dequeue() + " ");
			
		StdOut.print(randQ.dequeue() + " ");
		
		randQ.enqueue("is");
		
		//randQ.enqueue("worst");
		
		StdOut.println("(" + randQ.size() + " left on deque)");
		
		//StdOut.print(randQ.toString());
		
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
		///********************/
		StdOut.println("Test sample() method");
		
					
		StdOut.println("Test iteration");
		
		int n = 5;
		RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
		for (int i = 0; i < n; i++)
		    queue.enqueue(i);
		for (int a : queue) {
		    for (int b : queue)
		        StdOut.print(a + "-" + b + " ");
		    StdOut.println();
		}
	}

	
}
