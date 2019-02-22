package com.practice.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParanthesesProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Remove the minimum number of invalid parantheses to make the input string 
	 * valid. Return all possible results.
	 */
	public List<String> removeInvalidParentheses(String string) {
        List<String> results = new ArrayList<>();
        removeInvalidParentheses(results, string, 0, 0, new char[] {'(', ')'});
        return results;
    }
	
	public void removeInvalidParentheses(List<String> results, String str, int last_i, int last_j, char[] parens) {
		int stack = 0;
		for(int i = last_i; i < str.length(); i++) {
			if(str.charAt(i) == parens[0]) stack++;
			if(str.charAt(i) == parens[1]) stack--;
			if(stack >= 0) continue;
			for(int j = last_j; j <= i; j++) {
				if(str.charAt(j) == parens[1] && (j == last_j || str.charAt(j-1) == parens[0])) {
					removeInvalidParentheses(results, str.substring(0, j) + str.substring(j+1), i, j, parens);
				}
			}
		}
		String reversed = new StringBuilder(str).reverse().toString();
		if(parens[1] == '(') {
			results.add(reversed);
		} else {
			removeInvalidParentheses(results, str, 0, 0, new char[] {')', '('});
		}
	}
	
	/**
	 * Given a string, return the longest valid pair of parantheses
	 * 
	 * First collect all unmatched parentheses positions in a stack. Then
	 * iterate over the stack and find the maximum length between the
	 * unmatched positions.
	 */
	public int longestValidParantheses(String str) {
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				stack.push(i);
			} else {
				if(stack.isEmpty() || str.charAt(stack.peek()) == ')') {
					stack.push(i);
				} else {
					stack.pop();
				}
			}
		}
		if(stack.isEmpty()) // entire input string is valid
			return str.length();
		int a = 0, b = str.length(), longest = 0;
		while(!stack.isEmpty()) {
			a = stack.pop();
			longest = Math.max(longest, b-a-1);
			b = a;
		}
		longest = Math.max(longest, b);
		return longest;
 	}

}
