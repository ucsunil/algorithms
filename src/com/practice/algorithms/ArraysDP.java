package com.practice.algorithms;

import java.util.Arrays;
import java.util.Set;

public class ArraysDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Given a target distance, starting fuel amount, and fuel stations at certain distances with
	 * certain amounts of fuel, find the minimum number of stops a car would
	 * have to make to reach the target distance assuming one unit of fuel is 
	 * required to travel one unit of distance.
	 * 
	 * You may assume that the car has a fuel tank of infinite capacity and
	 * that at each stop it can transfer all the available fuel into the tank
	 */
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		int[] distance = new int[stations.length + 1];
		distance[0] = startFuel;
		
		for(int i = 0; i < stations.length; i++) {
			for(int t = 0; t <= i; t++) {
				if(distance[t] >= stations[i][0]) {
					distance[t+1] = Math.max(distance[t+1], distance[t] + stations[i][1]);
				}
			}
		}
		for(int i = 0; i < distance.length; i++) {
			if(distance[i] >= target) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Wiggle subsequence
	 * 
	 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers 
	 * strictly alternate between positive and negative. The first difference (if one exists) may be 
	 * either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
	 * 
	 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. 
	 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original 
	 * sequence, leaving the remaining elements in their original order.
	 * 
	 * Solutiion:
	 * For every position in the array, there are only three possible statuses for it.
	 * up position, it means nums[i] > nums[i-1]
	 * down position, it means nums[i] < nums[i-1]
	 * equals to position, nums[i] == nums[i-1]
	 * So we can use two arrays up[] and down[] to record the max wiggle sequence length 
	 * so far at index i. 
	 * If nums[i] > nums[i-1], that means it wiggles up. the element before it must be a down position. 
	 * 		so up[i] = down[i-1] + 1; down[i] keeps the same with before.
	 * If nums[i] < nums[i-1], that means it wiggles down. the element before it must be a up position. 
	 * 		so down[i] = up[i-1] + 1; up[i] keeps the same with before.
	 * If nums[i] == nums[i-1], that means it will not change anything becasue it didn't wiggle at all. 
	 * 		so both down[i] and up[i] keep the same.
	 */
	public int wiggleSubsequence(int[] sequence) {
		int[] up = new int[sequence.length];
		int[] down = new int[sequence.length];
		
		up[0] = 1;
		down[0] = 1;
		
		for(int i = 1; i < sequence.length; i++) {
			if(sequence[i] > sequence[i-1]) {
				up[i] = down[i-1] + 1;
				down[i] = down[i-1];
			} else if(sequence[i] < sequence[i-1]) {
				up[i] = up[i-1];
				down[i] = up[i-1] + 1;
			} else {
				up[i] = up[i-1];
				down[i] = down[i-1];
			}
		}
		return Math.max(up[sequence.length-1], down[sequence.length-1]);
	}
	
	/**
	 * Word Break
	 * 
	 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
	 * determine if s can be segmented into a space-separated sequence of one or more dictionary 
	 * words.
	 * 
	 * Input: s = "leetcode", wordDict = ["leet", "code"]
	 * Output: true
	 * Explanation: Return true because "leetcode" can be segmented as "leet code".
	 */
	public boolean wordBreak(String str, Set<String> dict) {
		boolean[] dp = new boolean[str.length()+1];
		dp[0] = true;
		
		for(int i = 1; i <= str.length(); i++) {
			for(int j = 0; j < i; j++) {
				if(dp[j] && dict.contains(str.substring(j, i))) {
					dp[i] = true;
				}
			}
		}
		return dp[str.length()];
	}
	
	/**
	 * Coin change
	 * 
	 * Given unlimited coins of certain denominations, find the least number of coins required
	 * to form a target sum
	 */
	public int coinChange(int[] coins, int target) {
		if(target < 1) return 0;
		
		int[] dp = new int[target+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		for(int coin : coins) {
			for(int i = coin; i <= target; i++) {
				if(dp[i-coin] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1+dp[i-coin]);
				}
			}
		}
		return dp[target];
	}

}
