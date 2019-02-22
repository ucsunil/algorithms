package com.practice.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingProblems {

	public static void main(String[] args) {
		BacktrackingProblems bp = new BacktrackingProblems();
		
		int[] array = new int[] {1, 2, 2};
		List<List<Integer>> results = bp.uniqueCombinationsForSum(array, 3);
		System.out.println(results);
	}
	
	/**
	 * Given an array of integers, find the different combinations that sum
	 * up to a certain value.
	 * 
	 * @param array
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> combinationsForSum(int[] array, int sum) {
		Arrays.sort(array);
		List<List<Integer>> results = new ArrayList<>();
		combinationsForSum(array, results, new LinkedList<Integer>(), sum, 0);
		return results;
	}
	
	public void combinationsForSum(int[] array, List<List<Integer>> results, List<Integer> list, int sum, int start) {
		if(sum < 0) {
			return;
		} else if(sum == 0) {
			results.add(new ArrayList<Integer>(list));
		} else {
			for(int i = start; i < list.size(); i++) {
				list.add(array[i]);
				combinationsForSum(array, results, list, sum-array[i], i); // since we can reuse the same number
				list.remove(list.size()-1);
			}
		}
	}
	
	
	/**
	 * Given an array of integers that contains duplicates, find the different unique
	 * combinations that sum up to a certain value.
	 * 
	 * @param array
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> uniqueCombinationsForSum(int[] array, int sum) {
		Arrays.sort(array);
		List<List<Integer>> results = new ArrayList<>();
		uniqueCombinationsForSum(array, results, new LinkedList<Integer>(), sum, 0);
		return results;
	}
	
	public void uniqueCombinationsForSum(int[] array, List<List<Integer>> results, List<Integer> list, int sum, int start) {
		if(sum < 0) {
			return;
		} else if(sum == 0) {
			results.add(new ArrayList<>(list));
		} else {
			for(int i = start; i < array.length; i++) {
				if(i > start && array[i] == array[i-1]) continue; // skip forward duplicates
				list.add(array[i]);
				uniqueCombinationsForSum(array, results, list, sum-array[i], i+1);
				list.remove(list.size()-1);
			}
		}
	}
	
	/**
	 * Given a certain number k and an upper limit n, find the different combinations of
	 * size k that sum up to a target. You can only use the numbers from 1...n
	 */
	public List<List<Integer>> combinationsForSumLimitK(int sum, int k, int n) {
		List<List<Integer>> results = new ArrayList<>();
		combinationsForSumLimitK(results, new ArrayList<Integer>(), sum, k, n, 1);
		return results;
	}
	
	public void combinationsForSumLimitK(List<List<Integer>> results, List<Integer> list, int sum, int k, int n, int start) {
		if(sum < 0) {
			return;
		} else if(list.size() == k && sum == 0) {
			results.add(new ArrayList<>(list));
		} else {
			for(int i = start; i <= n; i++) {
				list.add(i);
				combinationsForSumLimitK(results, list, sum-i, k, n, start+1);
				list.remove(list.size()-1);
			}
		}
	}
	
	/**
	 * Given a set of integers, find the different possible subset combinations
	 * that can be formed from this set
	 */
	public List<List<Integer>> subsets(int[] array) {
		List<List<Integer>> results = new ArrayList<>();
		subsets(array, results, new ArrayList<>(), 0);
		return results;
	}
	
	public void subsets(int[] array, List<List<Integer>> results, List<Integer> list, int start) {
		results.add(new ArrayList<>(list));
		for(int i = start; i < array.length; i++) {
			list.add(array[i]);
			subsets(array, results, list, i+1);
			list.remove(list.size()-1);
		}
	}
	
	/**
	 * Given an array of integers with duplicates, find the different possible subset
	 * combinations that can be formed from this array.
	 */
	public List<List<Integer>> subsets02(int[] array) {
		List<List<Integer>> results = new ArrayList<>();
		Arrays.sort(array);
		subsets02(array, results, new ArrayList<>(), 0);
		return results;
	}
	
	public void subsets02(int[] array, List<List<Integer>> results, List<Integer> list, int start) {
		results.add(new ArrayList<>(list));
		for(int i = 0; i < array.length; i++) {
			if(i > start && array[i] == array[i-1]) continue;
			list.add(array[i]);
			subsets02(array, results, list, i+1);
			list.remove(list.size()-1);
		}
	}
	
	/**
	 * Given a collection of distinct integers, return all possible
	 * permutations.
	 */
	public List<List<Integer>> permutations(int[] array) {
		List<List<Integer>> results = new ArrayList<>();
		permutations(array, results, new ArrayList<>());
		return results;
	}
	
	public void permutations(int[] array, List<List<Integer>> results, List<Integer> list) {
		if(list.size() == array.length) {
			results.add(new ArrayList<>(list));
		} else {
			for(int i = 0; i < array.length; i++) {
				if(list.contains(array[i])) continue;
				list.add(array[i]);
				permutations(array, results, list);
				list.remove(list.size()-1);
			}
		}
	}
	
	/**
	 * Given a collection of integers that may have duplicates, return all possible unique
	 * permutations
	 */
	public List<List<Integer>> uniquePermute(int[] array) {
		Arrays.sort(array);
		List<List<Integer>> results = new ArrayList<>();
		uniquePermute(array, results, new ArrayList<Integer>(), new boolean[array.length]);
		return results;
	}
	
	// (i > 0 && array[i] == array[i-1] && !used[i-1]) => if the previous element is the
	// same as the current element and is unused, then it means a permutation with an
	// ordering that would have been formed by using the current element in the ith
	// position already exists.
	public void uniquePermute(int[] array, List<List<Integer>> results, List<Integer> list, boolean[] used) {
		for(int i = 0; i < array.length; i++) {
			if(used[i] || (i > 0 && array[i] == array[i-1] && !used[i-1])) continue;
			used[i] = true;
			list.add(array[i]);
			uniquePermute(array, results, list, used);
			list.remove(list.size()-1);
			used[i] = false;
		}
	}
	
	/**
	 * Given a string, partition it into substrings such that each substring is a palindrome.
	 * Return all such possible partitions.
	 */
	public List<List<String>> partitionPalindromes(String str) {
		List<List<String>> results = new ArrayList<>();
		partitionPalindromes(str, results, new ArrayList<>(), 0);
		return results;
	}
	
	public void partitionPalindromes(String str, List<List<String>> results, List<String> list, int start) {
		if(start == str.length()) {
			results.add(new ArrayList<>(list));
		} else {
			for(int i = start; i < str.length(); i++) {
				if(isPalindrome(str, start, i)) {
					list.add(str.substring(start, i+1));
					partitionPalindromes(str, results, list, i+1);
					list.remove(list.size()-1);
				}
			}
		}
	}
	
	public boolean isPalindrome(String str, int low, int high) {
		while(low < high) {
			if(str.charAt(low) != str.charAt(high)) return false;
		}
		return true;
	}
}
