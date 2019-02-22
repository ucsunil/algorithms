package com.practice.algorithms;

public class BinarySearchTreeProblems {
	
	private static class Node {
		int value;
		Node left, right;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Convert to greater BT. Given a BST, convert it such that every key
	 * of the original BST is converted to the original key plus the sum of 
	 * all the nodes greater than it.
	 */
	int sumGBT = 0;
	public void convertToGreaterBT(Node root) {
		if(root == null) return;
		convertToGreaterBT(root.right);
		root.value = root.value + sumGBT;
		sumGBT = root.value;
		convertToGreaterBT(root.left);
	}
}
