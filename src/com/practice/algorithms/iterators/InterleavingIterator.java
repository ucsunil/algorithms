package com.practice.algorithms.iterators;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class InterleavingIterator<E> implements Iterator<E> {
	
	private List<Iterator<E>> iterators;
	private boolean[] done;
	private int current = 0;
	private int remaining = 0;
	
	public InterleavingIterator(List<Iterator<E>> iterators) {
		this.iterators = iterators;
		done = new boolean[iterators.size()];
		remaining = iterators.size();
	}

	@Override
	public boolean hasNext() {
		for(int i = 0; i < iterators.size(); i++) {
			if(iterators.get(i).hasNext())
				return true;
		}
		return false;
	}

	@Override
	public E next() {
		while(remaining > 0) {
			if(done[current]) {
				current++;
				if(current == iterators.size()) current = 0;
				continue;
			}
			if(iterators.get(current).hasNext()) {
				E element = iterators.get(current).next();
				current++;
				if(current == iterators.size()) current = 0;
				return element;
			} else {
				done[current] = true;
				remaining--;
				current++;
				if(current == iterators.size()) current = 0;
			}
		}
		throw new NoSuchElementException();
	}

}
