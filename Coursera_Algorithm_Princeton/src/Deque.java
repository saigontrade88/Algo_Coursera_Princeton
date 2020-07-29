import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*A double-ended queue or deque (pronounced �deck�) is 
 * a generalization of a stack and a queue 
 * that supports adding and removing items 
 * from either the front or the back of the data structure.
 * Design choice: implement stack and queue operations with singly linked list
*/
public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int n;
	
	//helper linked list class
	private class Node{
		private Item item;
		private Node next;
		public Node(Item item) {
			this.item = item;
		}
		
		
		
	}
	
	// construct an empty deque
    public Deque() {
    	first = null;
    	last = null;
    	n = 0;
    	assert check();
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return first == null;
    }

    // return the number of items on the deque
    public int size() {
    	return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	//save a link to the front list
    	Node oldFirst = first;
    	
    	//create a new node for the beginning
    	first = new Node(item);
    	    	
    	//link the new node to the front of the list
    	first.next = oldFirst;
    	
    	//increase the number of nodes
    	n++;
    	
    	assert check();
    }

    // add the item to the back
    public void addLast(Item item) {
    	//save a link to the last node
    	Node oldLast = last;
    	
    	// create a new node for the end
    	last = new Node(item);
    	
    	//link the new node to the end of the list
    	oldLast.next = last;
    	
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	
    	//save item to return
    	
    	//delete the first node -- -- link the second first node to the front of the list
    	
    	//return saved item
    	return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	//save item to return
    	
    	//delete the last node -- link the second last node to the end of the list
    	
    	//return the saved item
    	

    	return null;
    }

    // return an iterator over items in order from front to back
   	@Override
   	public Iterator<Item> iterator() {
   		// TODO Auto-generated method stub
   		return null;
   	}
   	
   	private class ListIterator implements Iterator<Item>{
   		
   		private Node current = first;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			return item;
		}
   		
   	}
   	//check internal invariants
   	private boolean check() {
   		if(n < 0) {
   			return false;
   		}
   		if(n == 0) {
   			if(first != null) return false;
   		}
   		else if(n == 1) {
   			if(first == null) return false;
   			if(first.next != null) return false;
   			//How about the tail check the Queue sample program
   		}
   		else {
   			if (first == null) return false;
   			if(first.next == null) return false;
   		}
   		return true;
   	}
   	
   	private  void printDequeue() {
   		//StringBuilder s = new StringBuilder();
   		Node temp = first;
   		String result = " ";
   		while(!(temp == null)) {
   			result += temp.item + " ";
   			temp = temp.next;
   		}
   		StdOut.println(result);
   	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<String> linkedDeque = new Deque<String>();
//		while(!StdIn.isEmpty()) {
//			String item = StdIn.readString();
//			if(!item.equals("-"))
//				linkedDeque.addFirst(item);
//			
//		}
		
		linkedDeque.addFirst("to");
		
		linkedDeque.addFirst("me");
		
		linkedDeque.addFirst("be");
		
		StdOut.println("(" + linkedDeque.size() + " left on deque)");
		
		linkedDeque.printDequeue();
		

	}



}
