package practice.datastructures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


/**
 * <code>ArrayPractice</code> includes a few static methods to practice handling 
 * arrays. <code>secondMin</code> works strictly with integer arrays, finding 
 * the second minimum element. Generic methods <code>firstNonRepeat</code> and 
 * <code>mergeArrays</code> can be used with arrays of any type. 
 * 
 * @author sarablinn
 *
 */
public class ArrayPractice {
	
	/**
	 * Finds the second minimum element of an integer array.
	 * @param int array
	 * @return int value of the second minimum
	 */
	public static int secondMin(int[] array) {
		int min = array[0];
		int secMin = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] <= min) {
				secMin = min;
				min = array[i];
				System.out.print("[" + min + ", " + secMin + "]");
			}
			if (array[i] > min && array[i] < secMin) {
				secMin = array[i];
				System.out.print("[" + min + ", " + secMin + "]");
			}
		}
		return secMin;
	}
	
//	———————————————————————————————————————————————————————————————————
	
	/**
	 * Helper class <code>CountIndex</code> is used to keep track index and
	 * count for use in counting repeating values in a data structure.
	 * 
	 * @author sarablinn
	 *
	 */
	private static class CountIndex{
		int count,index;
		
		public CountIndex(int index) {
			this.index = index;
			this.count = 1;
		}
		
		public void increaseCount() {
			this.count++;
		}
	}
	
	/**
	 * Returns index of first non-repeating integer in an array, 
	 * using static class <code>CountIndex</code>. If all integers repeat,
	 * will return -1.
	 * @param int array
	 * @return int
	 */
	public static <E> int firstNonRepeat(E[] array) {
		HashMap<E, CountIndex> hm = new HashMap<>(array.length);
		for (int i = 0; i < array.length; i++) {
			// if the hashmap already contains the same key, then increase the
			// count of that key's CountIndex value.
			if(hm.containsKey(array[i])) {
				hm.get(array[i]).increaseCount(); 
			}
			else {
				hm.put(array[i], new CountIndex(i));
			}
		}
		int result = -1; // -1 rather than Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			System.out.println("array index " + i + ": [hashmap key: " 
					+ array[i] + " hashmap value: index " + hm.get(array[i]).index
					+ ", count " + hm.get(array[i]).count + " ]");
			if (hm.get(array[i]).count == 1){ //&& result > hm.get(array[i]).index
				result = hm.get(array[i]).index; 
			}	
		}
		return result;
	}
	
	/**
	 * Merges two integer arrays and returns the resulting array.
	 * @param int[] array1
	 * @param int[] array2
	 * @return int[] mergedArray
	 */
	public static int[] mergeArrays(int[] arr1, int[] arr2) {
		int[] mergedArray = new int[arr1.length + arr2.length];
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr1.length; i++) {
			list.add(arr1[i]);
		}
		for (int i = 0; i < arr2.length; i++) {
			list.add(arr2[i]);
		}
		Collections.sort(list);
		for (int i = 0; i < mergedArray.length; i++) {
			mergedArray[i] = list.get(i);
		}
		return mergedArray;
	}
	
	/**
	 * Merges two arrays and returns the resulting array after first checking 
	 * that both arrays contain the same data type. Returns <code>null</code> 
	 * if the arrays are not of the same type.
	 * @param <E>
	 * @param arr1
	 * @param arr2
	 * @return mergedArray
	 */
	public static <E> E[] mergeArrays(E[] arr1, E[] arr2) {
		try {
			if(arr1.getClass().getSimpleName() == arr2.getClass().getSimpleName()) {
				Class<? extends Object[]> c = arr1.getClass();
				int newLength = arr1.length + arr2.length;
				@SuppressWarnings("unchecked")
				E[] mergedArray = (E[]) Array.newInstance(c, newLength);
				ArrayList<E> list = new ArrayList<>();
				list.addAll(Arrays.asList(arr1));
				list.addAll(Arrays.asList(arr2));
	
				for (int i = 0; i < newLength; i++) {
					mergedArray[i] = list.get(i);
				}
				return mergedArray;
			}
		} catch (Error e) {
			System.out.println("Error: Cannot merge arrays. "
					+ "Supplied arrays must be of same type.");
		}
		return null;
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// INTEGER ARRAYS USED FOR TESTING PURPOSES
		int[] p1_1 = {30, 1, 40, 3, 4, 9, 12, 2};
		int[] p1_2 = {5, 8, 3, -1, 2, 9};
		int[] p2 = {1, 3, 8, 7, 3, 1, 8, 1, 3, 7};
		
//		System.out.println("\n" + secondMin(p1_2));
		
//		System.out.println(firstNonRepeat(p2));
		
//		System.out.println(Arrays.toString(mergeArrays(p1_1,p1_2)));
		
		
	
	}

}
