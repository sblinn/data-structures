package practice.datastructures;


import java.util.LinkedList;

/**
 * <code>CircularLinkedList</code> is a generic representation of a Circular 
 * LinkedList, using static class <code>Node</code> to build the list of nodes. 
 * 
 * @author sarablinn
 *
 * @param <E>
 */
public class CircularLinkedList<E> extends LinkedList<E>{

	
	private static final long serialVersionUID = 1L;
	
	private Node<E> head = null;
	private Node<E >tail = null;
	private int size;
	
	/**
	 * Creates a node containing data and a link to the next node in the list.
	 * @author sarablinn
	 *
	 * @param <E>
	 */
	private static class Node <E>{ 
		protected E data; 
		protected Node<E> nextNode; 

		Node(E d) { 
			this.data = d; 
			this.nextNode = null; 
		} 
		
		public E getNode() {
			return this.data;
		}
		
		@SuppressWarnings("unused")
		public Node<E> nextNode() {
			return this.nextNode;
		}
	} 
	

	public CircularLinkedList() {
		super();
	}

	/**
	 * Checks if the LinkedList is circular and returns true if yes.
	 * @return boolean
	 */
	public boolean isCircularLinkedList() {
		Node<E> fastPtr = head;
		Node<E> slowPtr = head;
		while (fastPtr != null && fastPtr.nextNode != null) {
			fastPtr = fastPtr.nextNode.nextNode;
			slowPtr = slowPtr.nextNode;
			if (slowPtr == fastPtr)
				return true;
		}
		return false;
	}


	// if the head is empty — aka, the list isEmpty, we'll make 
	// the new node we add as both the head and tail of the list 
	// since there is only one node.
	// if the head isn't empty — aka, there are one or more elements 
	// already in the list, the existing tail should point to the 
	// new node and the newly added node will become the tail.
	// In both cases, the nextNode for tail will point to head.
	
	/**
	 * Adds a new node to the <code>CircularLinkedList</code>, returns
	 * true if successful, false if not.
	 * @return boolean
	 */
	@Override
	public boolean add(E e) {
		Node<E> newNode = new Node<E>(e);

		if (this.isEmpty()) {
			head = newNode;
			tail = newNode;  
			newNode.nextNode = head; 
			size++;
			return true;
		} 
		else {
			// tail will point to new node. 
			tail.nextNode = newNode;
			// New node will become new tail.  
			tail = newNode; 
			// tail will point to head.  
			tail.nextNode = head; 
			size++;
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "[]";
		}
		else {
			String str = "";
			Node<E> current = this.head;
			str += current.getNode() + ", ";
			current = current.nextNode; 

			while (current != head) { 
				if (current.nextNode == head) {
					str += current.getNode();
					current = current.nextNode;
				}
				else {
					str += current.getNode() + ", ";
					current = current.nextNode;
				}
			}
			return "[" + str + "]";
		}  
	}


	
	public static void main(String[] args) {

		CircularLinkedList<Integer> cll = new CircularLinkedList<>();

		cll.add(13);
		cll.add(7);
		cll.add(24);
		cll.add(1);
		cll.add(8);
		cll.add(37);
		cll.add(46);
		
		System.out.println(cll.toString());
		
		System.out.println(cll.isCircularLinkedList());
		
	}
}
