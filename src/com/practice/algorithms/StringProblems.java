package com.practice.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringProblems {

	public static void main(String[] args) {
		StringProblems sp = new StringProblems();
		
		String str = "abcdefaxyza";
		System.out.println("Longest substring in \"abcdefaxyza\" with non repeating characters is: " + sp.lengthOfLongestSubstringWithoutRepeatingChars(str));

	}
	
	/**
	 * Minimum window substring
	 */
	public String minimumWindowSubstring(String s1, String s2) {
		Map<Character, Integer> map = new HashMap<>();
		for(char c : s2.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		int head = 0, begin = 0, end = 0, window = Integer.MAX_VALUE, count = s2.length();
		while(end < s1.length()) {
			char c = s1.charAt(end);
			if(map.containsKey(c)) {
				if(map.get(c) > 0) 
					count--;
				map.put(c, map.get(c)-1);
			}
			end++;
			
			while(count == 0) { // means the window contains s2
				if(end - begin < window) {
					window = end - begin;
					head = begin;
				}
				char x = s2.charAt(begin);
				if(map.containsKey(x)) {
					map.put(x, map.get(x)+1);
					if(map.get(x) > 1) 
						count++;
				}
				begin++;
			}
		}
		return window == Integer.MAX_VALUE ? "" : s1.substring(head, head+window);
	}
	
	/**
	 * Longest substring with non repeating characters
	 */
	public int lengthOfLongestSubstringWithoutRepeatingChars(String str) {
        if(str.length() == 0) return 0;
        int max = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        
        int j = 0;
        for(int i = 0; i < str.length(); i++) {
        		if(map.containsKey(str.charAt(i))) {
        			j = Math.max(j, str.charAt(i));
        		}
        		map.put(str.charAt(i), i);
        		max = Math.max(max, i-j+1);
        }
        return max;
    }
	
	/**
	 * Given a string, find the length of the longest substring where each character
	 * occurs no less than k times.
	 */
	public int longestSubstringWithEachCharKTimes(String str, int k) {
		return longestSubstringWithEachCharKTimes(str.toCharArray(), 0, str.length(), k);
	}
	
	// use a divide and conquer approach
	public int longestSubstringWithEachCharKTimes(char[] chars, int begin, int end, int k) {
		if(end - begin < k) return 0;
		
		int[] occurrences = new int[26];
		for(int i = begin; i < end; i++) {
			int j = chars[i] - 'a';
			occurrences[j]++;
		}
		
		for(int i = 0; i < 26; i++) {
			if(occurrences[i] > 0 && occurrences[i] < k) { // ignore the rest
				for(int j = begin; j < end; j++) {
					if(chars[j] == i + 'a') {
						int left = longestSubstringWithEachCharKTimes(chars, begin, j, k); 
						int right = longestSubstringWithEachCharKTimes(chars, j+1, end, k);
						return Math.max(left, right);
					}
				}
			}
		}
		return end - begin;
	}
	
	/**
	 * Word Break II
	 * 
	 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
	 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return 
	 * all such possible sentences.

	 * Note:
	 * The same word in the dictionary may be reused multiple times in the segmentation.
	 * You may assume the dictionary does not contain duplicate words.
	 * Example:

	 * Input:
	 * s = "catsanddog"
	 * wordDict = ["cat", "cats", "and", "sand", "dog"]
	 * Output:
	 * [
  		"cats and dog",
  		"cat sand dog"
		]
	 */
	public List<String> wordBreakII(String str, Set<String> wordDict) {
        return wordBreakIIDFS(str, wordDict, new HashMap<String, List<String>>());
    }
	
	// use a DFS + memoization approach
	public List<String> wordBreakIIDFS(String str, Set<String> wordDict, Map<String, List<String>> map) {
		if(map.containsKey(str)) {
			return map.get(str);
		}
		
		List<String> result = new LinkedList<String>();
		if(str.length() == 0) {
			result.add("");
			return result;
		}
		
		for(String word : wordDict) {
			if(str.startsWith(word)) {
				List<String> subList = wordBreakIIDFS(str.substring(word.length()), wordDict, map);
				for(String s : subList) {
					result.add(word + (s.isEmpty() ? "" : " " + s));
				}
			}
		}
		map.put(str, result);
		return result;
	}
	
	/**
	 * Distinct subsequences II
	 * Given a string S, count the number of distinct, non-empty subsequences of S .
	 * 
	 * Input: "abc"
	 * Output: 7
	 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc"
	 */
	public int distinctSubsequencesII(String str) {
		int[] dp = new int[str.length()];
		Arrays.fill(dp, 1);
		int result = 0;
		
		for(int i = 0; i < str.length(); i++) {
			for(int j = 0; j < i; j++) {
				if(str.charAt(j) == str.charAt(i)) { // only when they are different
					dp[i] = dp[i] + dp[j];
				}
			}
			result = result + dp[i];
		}
		return result;
	}
	

}
