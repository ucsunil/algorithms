package com.practice.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeProblems {
	
	private static class Node {
		int value;
		Node left, right;
		
		Node(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Given a binary tree, find if there is a path from the root to a leaf
	 * that sums up to a certain value
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean rootToLeafPathSum(Node root, int sum) {
		if(root == null) return false;
		if(root.left == null && root.right == null && root.value-sum == 0) return true;
		return rootToLeafPathSum(root.left, sum-root.value) || rootToLeafPathSum(root.right, sum-root.value);
	}
	
	/**
	 * Given a binary tree, find all the paths from root to leaf that sum
	 * up to a particular value
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> rootToLeafPaths(Node root, int sum) {
		List<List<Integer>> results = new LinkedList<>();
		rootToLeafPaths(results, new LinkedList<Integer>(), root, sum);
		return results;
	}
	
	public void rootToLeafPaths(List<List<Integer>> results, List<Integer> path, Node root, int sum) {
		if(root == null) {
			return;
		} 
		path.add(root.value);
		if(root.left == null && root.right == null && root.value == sum) {
			results.add(new LinkedList<Integer>(path));
		} else {
			rootToLeafPaths(results, path, root.left, sum-root.value);
			rootToLeafPaths(results, path, root.right, sum-root.value);
		}
		path.remove(path.size()-1);
	}
	
	/**
	 * Given a binary tree, find the mirror image for the tree
	 */
	public Node invertTree(Node root) {
		if(root == null)
			return root;
		
		Node temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
	
	/**
	 * Find the maximum diameter of a binary tree
	 */
	int maxDia = 0;
	public int maxDiameter(Node root) {
		maxDepth(root);
		return maxDia;
	}
	
	public int maxDepth(Node root) {
		if(root == null) return 0;
		
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		maxDia = Math.max(maxDia, left+right);
		
		return Math.max(left, right) + 1;
	}
	
	/**
	 * Leetcode 979
	 * Given the root of a binary tree with N nodes, each node in the tree has 
	 * node.val coins, and there are N coins total.
	 * In one move, we may choose two adjacent nodes and move one coin from one 
	 * node to another. (The move may be from parent to child, or from child to parent.)
	 * Return the number of moves required to make every node have exactly one coin.
	 * 
	 * Input: [3,0,0]
	 * Output: 2
 	 * Explanation: From the root of the tree, we move one coin to its left child, and 
 	 * one coin to its right child.
	 */
	int distributions = 0;
	public int distributeCoins(Node root) {
		distributeCoinsHelper(root);
		return distributions;
	}

	public int distributeCoinsHelper(Node root) {
		if(root == null) return 0;
		
		int left = distributeCoinsHelper(root.left);
		int right = distributeCoinsHelper(root.right);
		
		distributions = distributions + Math.abs(left) + Math.abs(right);		
		return left + right + root.value - 1;
	}
	
	/**
	 * Method 02 for the above problem
	 */
	public int distributeCoins02(Node root) {
		int result = 0;
		if(root.left != null) {
			result = result + distributeCoins02(root.left);
			root.value = root.value + root.left.value - 1;
			result = result + Math.abs(root.left.value - 1);
		}
		if(root.right != null) {
			result = result + distributeCoins02(root.left);
			root.value = root.value + root.right.value - 1;
			result = result + Math.abs(root.right.value - 1);
		}
		return result;
	}

}
