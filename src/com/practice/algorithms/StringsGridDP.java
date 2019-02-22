package com.practice.algorithms;

public class StringsGridDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String longestCommonSubstring(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		
		int max = 0, maxi = 0, maxj = 0;
		for(int i = 1; i < dp.length; i++) { // row 0 is all 0s
			for(int j = 1; j < dp[0].length; j++) { // column 0 is all 0s
				if(s1.charAt(i-1) == s2.charAt(i-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				if(dp[i][j] > max) {
					max = dp[i][j];
					maxi = i;
					maxj = j;
				}
			}
		}
		if(max == 0) return "";
		return s1.substring(maxi-max, maxi+1);
	}
	
	/**
	 * Given two strings s1 and s2, find the minimum edit (insert, replace, delete) actions
	 * required to convert one to another (s1 to s2 will require same number of actions as
	 * s2 to s1)
	 */
	public int minimumEditDistance(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		
		for(int i = 1; i <= s1.length(); i++) {
			for(int j = 1; j <= s2.length(); j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
	
	/**
	 * Given a string find the longest palindromic substring.
	 */
	public String longestPalindromicSubstring(String str) {
		boolean[][] dp = new boolean[str.length()][str.length()];
		String result = "";
		
		for(int i = str.length()-1; i >= 0; i--) {
			for(int j = i; j < str.length(); j++) {
				dp[i][j] = str.charAt(i) == str.charAt(j) && (j-i < 3 || dp[i+1][j-1]);
				
				if(dp[i][j] && j - i + 1 > result.length()) {
					result = str.substring(i, j+1);
				}
			}
		}
		return result;
	}
	
	/**
	 * Distinct Subsequences
	 * 
	 * Given a string S and a string T, count the number of distinct subsequences of S 
	 * which equals T
	 */
	public int distinctSubsequences(String s, String t) {
		int[][] dp = new int[t.length()+1][s.length()+1];
		
		for(int i = 1; i <= t.length(); i++) {
			for(int j = 1; j <= s.length(); j++) {
				if(t.charAt(i-1) == s.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
				} else {
					dp[i][j] = dp[i][j-1];
				}
			}
		}
		return dp[t.length()][s.length()];
	}

	/**
	 * 
	 */
}
