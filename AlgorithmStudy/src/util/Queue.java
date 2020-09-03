package util;

import java.util.NoSuchElementException;

public class Queue<T> {
	
	@SuppressWarnings("hiding")
	private class Node<T> {
		private Node<T> next;
		private T t;
		
		private Node(T t){
			this.t = t;
		}
		
	}

	private Node<T> first;
	private Node<T> last;
	
	
	public void add(T t) {
		if (first == null) {
			first = new Node<T>(t);
			last = first;		

		} else {
			last = last.next = new Node<T>(t);
		}		
	}
	
	public T remove() {
		if(first == null) {
			throw new NoSuchElementException();
		}
		
		T t = first.t;
		
		if(first == last) {
		first = null;
		last = null;
			
		}else {
		first = first.next;
		}
		
		return t;
	}
	public T peek() {
		if(this.isEmpty()) {
			return null;
		}
		return first.t;
		
	}
	public boolean isEmpty() {
		if(first == null) {
			return true;
		} 
		return false;
	}	

}
