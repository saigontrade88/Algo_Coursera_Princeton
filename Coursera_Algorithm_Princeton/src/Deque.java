import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int n;
	
	//helper linked list class
	private class Node{
		private Item item;
		private Node next;
		private Node prev;
		public Node(Item item) {
			this.item = item;
			next = prev = null;
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
    // edge case: the list is empty
    public void addFirst(Item item) {
    	
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	
    	//save a link to the front list
    	Node newNode = new Node(item);
    	
    	//create a new node for the beginning, make the new node as first
    	newNode.next = first;
    	newNode.prev = null;
    	    	
    	//make next of the new node as the old first
    	if(!isEmpty()) {
    		first.prev = newNode;
    	}
    	else {
    		last = newNode;
    	}
    	
    	first = newNode;
    	//increase the number of nodes
    	n++;
    	
    	assert check();
    }

    // add the item to the back
    public void addLast(Item item) {
    	
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null");
    	}
    	
    	//save a link to the last node
    	Node newNode = new Node(item);
    	
    	//make the new node as last
    	newNode.next = null;
    	newNode.prev = last;
    	
    	if(!isEmpty()) {
    		last.next = newNode;
    	}
    	else {
    		first = newNode;
    	}
    		
    	//link the new node to the end of the list
    	last = newNode;
    	
    	//increase the number of nodes
    	n++;
    	
    	assert check();
    	
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	
    	if(isEmpty()) {
    		throw new NoSuchElementException("Stack underflow");
    	}
    	//save item to return
    	Item oldFirst = first.item;
    	
    	//delete the first node -- -- link the second first node to the front of the list
    	Node firstNext = first.next;
    	
    	//if the delete node is not the last/tail node
    	if(firstNext != null) {
    		
    		firstNext.prev = null; //handle previous pointer
    	
    		first.next = null;//handle next pointer
    	
    		first = firstNext;
    	}
    	else {
    		
    		first = null;
    		last = null;
    	}
    	
    	n--;
    	
    	assert check();
    	
    	//return saved item
    	return oldFirst;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	
    	if(isEmpty()) {
    		throw new NoSuchElementException("Stack underflow");
    	}
    	
    	//save item to return
    	Item oldLast = last.item;
    	
    	//delete the last node -- link the second last node to the end of the list
    	Node prevLast = last.prev;
    	
    	//if the delete node is not the head node
    	if(prevLast != null) {
    		
    		prevLast.next = null; //handle next pointer
    	
    		last.prev = null;//handle prev pointer
    	
    		last = prevLast;
    	}
    	else {
    		//first.next = null; //handle next pointer
    		first = null;
    		last = null;
    	}
    	
    	
    	n--;
    	
    	assert check();
    	//return the saved item
    	return oldLast;
    }

    // return an iterator over items in order from front to back
   	@Override
   	public Iterator<Item> iterator() {
   		// TODO Auto-generated method stub
   		return new ListIterator();
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
			if(!hasNext())
				throw new UnsupportedOperationException("There are no more items to return");
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
   			if(first == null || last == null) return false;
   			if(first.next != null || last.prev != null) return false;
   			//How about the tail check the Queue sample program
   		}
   		else {
   			if (first == null) return false;
   			if(first.next == null || last.prev == null) return false;
   		}
   		return true;
   	}
 	  	
   	@Override
	public String toString() {
		// TODO Auto-generated method stub
   		StringBuilder s = new StringBuilder();
   		for(Item item: this)
   			s.append(item + " ");
		return s.toString();
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
		
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-") && !item.equals("*") && !item.equals("it"))
				linkedDeque.addLast(item);
			else if(item.equals("*"))
				linkedDeque.removeLast();
			else if(item.equals("it"))
				linkedDeque.addFirst(item);
			else
				linkedDeque.removeFirst();
			
		}
		
		StdOut.println("(" + linkedDeque.size() + " left on stack)");
		
		StdOut.println(linkedDeque.toString());
		
		//***********************************
			
//		StdOut.println("Call add  last 0 1 2 3, then removeLast repeatedly");
//		
//		linkedDeque.addLast("0");
//	
//		linkedDeque.addLast("1");
//		
//		linkedDeque.addLast("2");
//		
//		linkedDeque.addLast("3");
//		
//		StdOut.println("(" + linkedDeque.size() + " left on deque)");
//		
//		linkedDeque.printDequeue();
//		
//		String result = " ";
//		
//		int origSize = linkedDeque.size();
//		
//		StdOut.println("Call removeLast " + origSize + " consecutive times: " + result);
//		
//			
//		for(int i = 0; i < origSize; i++) {
//			
//			result += linkedDeque.removeLast() + " ";
//			
//			//StdOut.println("Remove items in this order: " + result);
//			
//		}
//		
//		StdOut.println("Remove items in this order: " + result);
//		StdOut.println("(" + linkedDeque.size() + " left on deque)");
//		
//		//***********************************
//		StdOut.println("Test case 2: Call add  first 0 1 2 3, then removeLast repeatedly");
//		
//		result = " ";
//		
//		linkedDeque.addFirst("0");
//	
//		linkedDeque.addFirst("1");
//		
//		linkedDeque.addFirst("2");
//		
//		linkedDeque.addFirst("3");
//		
//		StdOut.println("(" + linkedDeque.size() + " left on deque)");
//		
//		linkedDeque.printDequeue();
//		
//		origSize = linkedDeque.size();
//		
//		StdOut.println("Call removeLast " + origSize + " consecutive times: " + result);
//		
//			
//		for(int i = 0; i < origSize; i++) {
//			
//			result += linkedDeque.removeLast() + " ";
//			
//			//StdOut.println("Remove items in this order: " + result);
//			
//		}
//		
//		StdOut.println("Remove items in this order: " + result);
//		StdOut.println("(" + linkedDeque.size() + " left on deque)");
		
		
		

	}



}
