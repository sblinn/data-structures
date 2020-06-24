package practice.datastructures;

import java.util.Collections;
import java.util.Stack;
import java.util.regex.Pattern;


/**
 * Practice using Stack data structure.
 * 
 * @author sarablinn
 *
 */
public class StackPractice {

	
	/**
	 * Reverse a String using Stack.
	 * @param String 
	 * @return String
	 */
	public static String stackReverseString(String str) {
		Stack<Character> stack = new Stack<>();
		String newStr = "";
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
		}
		for (int i = 0; i < str.length(); i++) {
			newStr += Character.toString(stack.pop());
		}
		return newStr;
	}
	
	/**
	 * Uses a stack data structure to evaluate a postfix equation
	 * (operand operand operator).
	 * @param String postfix
	 * @return Integer
	 */
	public static Integer stackEvalPostfix(String postfix) {
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < postfix.length(); i++) {
			String pfc = Character.toString(postfix.charAt(i));
			// if the character is a number, add it to the stack
			if (Pattern.matches("[0-9]", pfc)) {
				stack.push(Integer.parseInt(String.valueOf(pfc)) );
			}
			// if its an operator, pop the last two items and equate, 
			// push the result back to the top of the stack.
			else if (Pattern.matches("[+-/]", pfc) || pfc.equals("*")) {
				int op2 = stack.pop();
				int op1 = stack.pop();
				
				switch(pfc) {
				case "+": stack.push(op1 + op2); break;
				case "-": stack.push(op1 - op2); break;
				case "*": stack.push(op1 * op2); break;
				case "/": stack.push(op1 + op2); break;
				}
			}
		}
		return stack.pop();
	}
	
	
	/**
	 * Sorts and returns a stack of integers. Method can be replicated
	 * for objects, just override compareTo() in the object's class to
	 * specify what would make one object greater than another.
	 * @param stack
	 * @return stack
	 */
	public static Stack<Integer> sortStack(Stack<Integer> stack){
//		Stack<Integer> sorted = new Stack<>();
		Collections.sort(stack);
		return stack;
	}
	
	// If the current character is a starting bracket (‘(‘ or ‘{‘ or ‘[‘) 
	// then push it to stack. If the current character is a 
	// closing bracket (‘)’ or ‘}’ or ‘]’) then pop from stack 
	// and if the popped character is the matching starting bracket 
	// then fine else parenthesis are not balanced.
	// After complete traversal, if there is some starting bracket 
	// left in stack then “not balanced”
	
	/**
	 * Checks if the <code>String</code> input has balanced brackets, using
	 * a stack implementation.
	 * @param str
	 * @return boolean
	 */
	public static boolean isBalancedBrackets(String str) {
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			// Push OPEN brackets to the stack
			if (str.charAt(i) == '{' || str.charAt(i) == '[' 
					|| str.charAt(i) == '(') {
				stack.push(str.charAt(i));
			}
			// When encountering CLOSE bracket, pop the last item
			// from the stack, create a String of the popped item and
			// the the CLOSE bracket and use separate method to check 
			// that they are a matching type.
			else if (str.charAt(i) == '}' || str.charAt(i) == ']' 
					|| str.charAt(i) == ')') {
				char c = stack.pop();
				String check = Character.toString(c) + str.charAt(i);
				if (!isMatchingBracket(check)) {
					return false;
				}
			}
		}
		if (!stack.empty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Helper method to <code>isBalancedBrackets</code> for checking if
	 * a given string contains a matching pair of brackets. 
	 * @param check
	 * @return boolean
	 */
	public static boolean isMatchingBracket(String check) {
		String curlies = "{}";
		String brackets = "[]";
		String parents = "()";
		
		if (check.equals(curlies) || check.equals(brackets) 
				|| check.equals(parents)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
//		Stack<Integer> stack = new Stack<>();
//		stack.add(6);
//		stack.add(3);
//		stack.add(4);
//		stack.add(9);
//		stack.add(1);
		
//		System.out.println(stack.toString());
//		
//		System.out.println(sortStack(stack).toString());
		
//		String str = "{Dog(Cat)[Shoe]}";
//		System.out.println(isBalancedBrackets(str));
//		
//		
//		System.out.println(stackEvalPostfix("2 3 1 * + 9 -"));

//		System.out.println(stackReverseString("Hopscotch!"));
	}

}
