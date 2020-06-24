package practice.datastructures;


import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;


/**
 * Practice using and modifying <code>LinkedList</code> data structure.
 * Created generic methods to allow handling LinkedLists containing any type.
 * 
 * @author sarablinn
 *
 */
public class LinkedListPractice {


	/**
	 * Reverse a given LinkedList.
	 * @param LinkedList
	 */
	public static <E> void reverseLinkedList(LinkedList<E> ll) {
		LinkedList<E> ll2 = new LinkedList<E>(ll);
		ll.clear();
		while (!ll2.isEmpty()) {
			ll.push(ll2.removeFirst());
		}
	}
	
	/**
	 * Utilizes Iterator to iterate and remove every element in 
	 * the LinkedList.
	 * @param LinkedList
	 */
	public static <E> void killLinkedList(LinkedList<E> ll) {
		Iterator<E> iter = ll.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
			System.out.println(ll.toString());
		}
	}
	
	/**
	 * Return the element at the index Nth from the end of
	 * the LinkedList.
	 * @param LinkedList<E>
	 * @param int
	 * @return Element at index n from the end.
	 */
	public static <E> E getNodeN(LinkedList<E> linkedList, int n) {
		int index = linkedList.size() - (n+1);
		return linkedList.get(index);
	}
	
	/**
	 * Removes any duplicate values. 
	 * @param <E>
	 * @param linkedList
	 */
	public static <E> void clearDuplicates(LinkedList<E> linkedList) {
		LinkedHashSet<E> hashSet = new LinkedHashSet<>();
		for (int i = 0; i < linkedList.size(); i++) {
			if (!hashSet.contains(linkedList.get(i))) {
				hashSet.add(linkedList.get(i));
			}
			else {
				linkedList.remove(i);
			}
		}
	}
	
	public static void main(String[] args) {

		LinkedList<String> ll = new LinkedList<>();

		ll.add("a");
		ll.add("b");
		ll.add("c");
		ll.add("c");
		ll.add("d");
		ll.add("e");
		ll.add(1, "k");
		ll.add("c");
		
		
		
		System.out.println(ll.toString());
//		reverseLinkedList(ll);
//		System.out.println(ll.toString());
//		
//		killLinkedList(ll);
//		
//		System.out.println(ll.element());
		
//		System.out.println(getNodeN(ll, 2));
		 
		clearDuplicates(ll);
		System.out.println(ll.toString());
		
	}

}
