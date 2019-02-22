package com.practice.algorithms.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestClass {

	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
		List<Integer> list2 = Arrays.asList(1, 2, 3, 4);
		List<Integer> list3 = Arrays.asList(1, 2, 3, 4);
		List<Integer> list4 = Arrays.asList();
		
		List<Iterator<Integer>> list = Arrays.asList(list1.iterator(), list2.iterator(), list3.iterator(), list4.iterator());
		
		InterleavingIterator<Integer> it = new InterleavingIterator<>(list);
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

}
