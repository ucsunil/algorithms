package com.practice.algorithms;

public class GridsDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Given a robot at the top left corner of an MxN grid, find the
	 * different possible ways the robot can reach the bottom right
	 * corner assuming it can only move right or down at each step
	 */
	public int uniquePaths(int M, int N) {
		int[][] dp = new int[M][N];
		
		for(int i = 0; i < M; i++) { // left most column
			dp[i][0] = 1;
		}
		for(int j = 0; j < N; j++) { // top most row
			dp[0][j] = 1;
		}
		for(int i = 1; i < M; i++) {
			for(int j = 1; j < N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[M-1][N-1];
	}
	
	/**
	 * Given a robot at the top left corner of an MxN grid, find the
	 * different possible ways the robot can reach the bottom right
	 * corner assuming it can only move right or down at each step.
	 * There may be obstacles in the grid which are represented by -1
	 */
	public int uniquePathsWithObstacles(int[][] grid) {
		int M = grid.length;
		int N = grid[0].length;
		int[][] dp = new int[M][N];
		
		for(int i = 0; i < M; i++) { // left most column
			if(grid[i][0] == -1) break;
			dp[i][0] = 1;
		}
		for(int j = 0; j < N; j++) { // top most row
			if(grid[0][j] == -1) break;
			dp[0][j] = 1;
		}
		
		for(int i = 1; i < M; i++) {
			for(int j = 1; j < N; j++) {
				if(grid[i][j] == -1) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}
		return dp[M-1][N-1];
	}

}
