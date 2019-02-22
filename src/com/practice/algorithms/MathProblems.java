package com.practice.algorithms;

public class MathProblems {

	public static void main(String[] args) {
		
	}
	
	/**
	 * Get the integer square root of a given number
	 * 
	 * @param x
	 * @return
	 */
	public int getIntegerSquareRoot(int x) {
		if(x == 0 || x == 1) return x;
		int start = 1, end = x/2;
		
		while(true) {
			int mid = start + (end - start)/2;
			if(mid > x/mid) {
				end = mid - 1;
			} else {
				if((mid+1) > x/(mid+1))
					return mid;
				start = mid + 1;
			}
		}
	}

}
