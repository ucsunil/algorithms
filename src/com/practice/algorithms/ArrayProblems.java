package com.practice.algorithms;

import java.util.Arrays;

public class ArrayProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Given an array, find the maximum product that can be formed
	 * from a subarray
	 */
	public int maximumProductSubarray(int[] nums) {
		int result = nums[0];
		
		// imax, imin is the max/min product of subarray
		// ending at i
		for(int i = 1, imax = result, imin = result; i < nums.length; i++) {
			if(nums[i] < 0) { // swap => multiplying with negative makes bigger number
				// smaller and smaller number bigger
				int temp = imax;
				imax = imin;
				imin = temp;
			}
			
			imax = Math.max(nums[i], imax*nums[i]);
			imin = Math.min(nums[i], imin*nums[i]);
			
			result = Math.max(imax, result);
		}
		return result;
	}
	
	/**
	 * Given an array of non-negative integers, you are initially positioned 
	 * at the first index of the array. Each element in the array represents 
	 * your maximum jump length at that position.
	 *
	 * Determine if you are able to reach the last index.
	 */
	public boolean canJump(int[] array) {
		for(int i = 0, reach = 0; i < array.length && i <= reach; i++) {
			reach = Math.max(i+array[i], reach);
			if(reach >= array.length-1) return true;
		}
		return false;
	}
	
	/**
	 * Given an array of non-negative integers, you are initially positioned 
	 * at the first index of the array. Each element in the array represents 
	 * your maximum jump length at that position.
	 * 
	 * Your goal is to reach the last index in the minimum number of jumps.
	 */
	public int jump(int[] nums) {
        int[] jumps = new int[nums.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        
        for(int i = 0; i < nums.length; i++) {
            int dist = nums[i];
            while(dist > 0) {
                if(i+dist >= nums.length) {
                    dist--;
                    continue;
                }
                jumps[i+dist] = Math.min(1 + jumps[i], jumps[i+dist]);
                dist--;
            }
        }
        return jumps[nums.length-1];
    }

}
