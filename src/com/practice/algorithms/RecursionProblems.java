package com.practice.algorithms;

import java.util.ArrayList;
import java.util.List;

public class RecursionProblems {

	public static void main(String[] args) {
		RecursionProblems rp = new RecursionProblems();
		List<String> strings = rp.generateParanthesesPairs(2);
		System.out.println(strings);
	}
	
	/**
	 * Given a string containing only three types of characters: '(', ')' and '*', 
	 * write a function to check whether this string is valid. We define the validity 
	 * of a string by these rules:
	 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
	 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
	 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
	 * 4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
	 * 5. An empty string is also valid.
	 */
	public boolean checkValidString(String str) {
		return checkValidString(str, 0, 0);
	}
	
	public boolean checkValidString(String str, int start, int count) {
		for(int i = start; i < str.length(); i++) {
			if(count < 0) return false;
			if(str.charAt(i) == '(') {
				count++;
			} else if(str.charAt(i) == ')') {
				count--;
			} else if(str.charAt(i) == '*'){ // there are 3 possibilities
				return checkValidString(str, i+1, count+1) || checkValidString(str, i+1, count-1) || checkValidString(str, i+1, count);
			}
		}
		return count == 0;
	}
	
	public interface NestedInteger {
		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();
		
		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();
		
		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
	
	/**
	 * Given a list of nested weights, find the nested list weight sum inverse
	 */
	public int nestedIntegerListSumInverse(List<NestedInteger> nis) {
		List<List<Integer>> list = new ArrayList<>();
		for(NestedInteger ni : nis) {
			nestedIntegerListSumInverse(ni, list, 0);
		}
		int j = list.size(), sum = 0;
		for(int i = 0; i < list.size(); i++) {
			for(int x : list.get(i)) {
				sum += x*j;
			}
			j--;
		}
		return sum;
	}
	
	public void nestedIntegerListSumInverse(NestedInteger ni, List<List<Integer>> list, int level) {
		if(list.size() == level) {
			list.add(new ArrayList<Integer>());
		}
		if(ni.isInteger()) {
			list.get(level).add(ni.getInteger());
		} else {
			for(NestedInteger x : ni.getList()) {
				nestedIntegerListSumInverse(x, list, level+1);
			}
		}
	}
	
	/**
	 * Generate valid string of N parantheses pairs
	 */
	public List<String> generateParanthesesPairs(int N) {
		List<String> strings = new ArrayList<>();
		generateParanthesesPairs(strings, "", N, 0, 0);
		return strings;
	}
	
	public void generateParanthesesPairs(List<String> strings, String str, int N, int open, int closed) {
		if(str.length() == N*2) {
			strings.add(str);
			return;
		}
		if(open < N) {
			generateParanthesesPairs(strings, str + "(", N, open+1, closed);
		} 
		if(closed < open) {
			generateParanthesesPairs(strings, str + ")", N, open, closed+1);
		}
	}

}
