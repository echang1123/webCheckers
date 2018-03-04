package com.webcheckers.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that is a generic reverse iterator
 */
public class ReverseIterator< E > implements Iterator< E > {

	private int index; // the starting index
	private ArrayList< E > list; // the array list


	/**
	 * Constructor for the generic reverse iterator
	 * @param list the list
	 */
	public ReverseIterator( ArrayList< E > list ) {
		this.index = list.size() - 1;
		this.list = list;
	}


	/**
	 * Whether the list has a next element
	 * @return true / false
	 */
	public boolean hasNext() {
		return this.index >= 0;
	}


	/**
	 * Return current element and go to next
	 * @return the current element
	 */
	public E next() {
		if( this.hasNext() ) {
			int current = this.index;
			this.index--;
			return list.get( current );
		}
		throw new NoSuchElementException();
	}
}