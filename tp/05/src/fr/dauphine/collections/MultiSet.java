package fr.dauphine.collections;

import java.util.HashMap;
import java.util.Map;

public class MultiSet<E> {
	private final Map<E, Integer> multiSet;
	
	public MultiSet() {
		this.multiSet = new HashMap<>();
	}
	
	public void add(E e) {
		int count = multiSet.getOrDefault(e, 0);
		multiSet.put(e, count+1);
	}
	
	public int get(E e) {
		return count(e);
	}
	
	public boolean contains(E e) {
		return count(e) > 0;
	}
	
	public void remove(E e) {
		int count = count(e);
		
		if (count == 0) return;
		else if (count == 1) multiSet.remove(e);
		else if (count > 1) multiSet.put(e, count-1);
	}	
	
	private int count(E e) {
		return multiSet.getOrDefault(e, 0);
	}
	
}
