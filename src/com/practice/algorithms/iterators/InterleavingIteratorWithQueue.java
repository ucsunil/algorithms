package com.practice.algorithms.iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class InterleavingIteratorWithQueue<E> implements Iterator<E> {
	
	private Queue<Iterator<E>> queue;
	
	public InterleavingIteratorWithQueue(List<Iterator<E>> list) {
		this.queue = new LinkedList<>();
		for(Iterator<E> iterator : list) {
			if(iterator.hasNext())
				queue.add(iterator);
		}
	}

	@Override
	public boolean hasNext() {
		return queue.size() == 0 ? false : true;
	}

	@Override
	public E next() {
		if(queue.size() == 0) {
			throw new NoSuchElementException();
		}
		Iterator<E> iterator = queue.poll();
		E element = iterator.next();
		if(iterator.hasNext()) {
			queue.add(iterator);
		}
		return element;
	}
	
	

}
