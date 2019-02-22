package com.practice.algorithms.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SumOfDistancesInTree {
	
	public static void main(String[] args) {
		SumOfDistancesInTree solution = new SumOfDistancesInTree();
		
		int[][] edges = {{0,1}, {1,2}};
		int[] result = solution.sumOfDistancesInTree(3, edges);
		
		for(int x : result) {
			System.out.print(x + " ");
		}
	}
	
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
        List<Set<Integer>> list = new ArrayList<>(); // adjacency list
        
        for(int i = 0; i < N; i++) {
        		list.add(new HashSet<Integer>());
        }
        for(int[] edge : edges) { // build the adjacency list
        		list.get(edge[0]).add(edge[1]);
        		list.get(edge[1]).add(edge[0]);
        }
        
        int[] count = new int[N]; // count holds the number of nodes in subtree i
        int[] result = new int[N]; // holds the result for subtree i
        dfs(list, count, result, 0, new HashSet<Integer>());
        dfs2(list, N, count, result, 0, new HashSet<Integer>());
        return result;
    }
	
	/**
	 * The first traversal is a post order traversal that is used to get a count of the nodes under
	 * each node in the graph and the sum of all distances from each node to the root.
	 */
	public void dfs(List<Set<Integer>> list, int[] count, int[] result, int root, Set<Integer> visited) {
		visited.add(root);
		// leaf nodes will have only one entry here and will not satisfy the if condition in the for loop
		for(int node : list.get(root)) { 
			if(!visited.contains(node)) {
				dfs(list, count, result, node, visited);
				count[root] = count[root] + count[node];
				result[root] = result[root] + result[node] + count[node];
			}
		}
		count[root]++; // has to happen regardless
	}
	
	/**
	 * The second traversal is a pre order traversal where the distance sum for each node is calculated with the
	 * help of result[root] calculate using the first DFS. 
	 */
	public void dfs2(List<Set<Integer>> list, int N, int[] count, int[] result, int root, Set<Integer> visited) {
		visited.add(root);
		for(int node : list.get(root)) {
			if(!visited.contains(node)) {
				result[node] = result[root] - count[node] + N - count[node];
				dfs2(list, N, count, result, node, visited);
			}
		}
	}

}
