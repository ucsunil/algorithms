package com.practice.algorithms;

public class Grids {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean wordExists(String word, char[][] grid) {
		char[] chars = word.toCharArray();
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(wordExists(chars, grid, 0, 0, 0, new boolean[grid.length][grid[0].length])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean wordExists(char[] chars, char[][] grid, int x, int y, int index, boolean[][] visited) {
		if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y]) 
			return false;
		if(grid[x][y] != chars[index]) 
			return false;
		
		visited[x][y] = true;
		if(wordExists(chars, grid, x+1, y, index+1, visited)
				|| wordExists(chars, grid, x, y+1, index+1, visited)
				|| wordExists(chars, grid, x-1, y, index+1, visited)
				|| wordExists(chars, grid, x, y-1, index+1, visited)) {
			return true;
		}
		visited[x][y] = false;
		return false;
	}

}
