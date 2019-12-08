package fr.dauphine.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class WithTime {
	
	public static void withTime(Supplier<List<Integer>> s, Consumer<List<Integer>> c) {
		List<Integer> l = s.get();
		long startTime = System.nanoTime();
		c.accept(l);
		long endTime = System.nanoTime();
		System.out.println(endTime-startTime);
	}
	
	public static void withTimeForAlgoOnList(
		Supplier<List<Integer>> s, 
		//Function<List<Integer>, List<Integer>> f,
		Consumer<List<Integer>> change,
		Consumer<List<Integer>> c
	) {
		//l = f.apply(l);
		List<Integer> l = s.get();
		change.accept(l);
		long startTime = System.nanoTime();
		c.accept(l);
		long endTime = System.nanoTime();
		System.out.println(endTime-startTime);
	}
	
	/*
	public static Function<List<Integer>, List<Integer>> initList(int size, int seed) {
		return new Function<List<Integer>, List<Integer>>() {
			@Override
			public List<Integer> apply(List<Integer> l) {
				Random random = new Random(seed);
				for(int i = 0 ; i < size ; ++i) {
					l.add(random.nextInt());
				}
				return l;
			}
		};
	}
	*/
	
	public static Consumer<List<Integer>> listInitialiser(int size, int seed) {
		return new Consumer<List<Integer>>() {
			@Override
			public void accept(List<Integer> l) {
				Random random = new Random(seed);
				for(int i = 0 ; i < size ; ++i) {
					l.add(random.nextInt());
				}
			}
		};
	}
	
	public static void test1() {
		withTimeForAlgoOnList(
			() -> {return new LinkedList<Integer>();}, 
			listInitialiser(100, 0),  
			(l) -> {Collections.sort(l);}
		);
			
		withTimeForAlgoOnList(
			() -> {return new ArrayList<Integer>();}, 
			listInitialiser(100, 0),  
			(l) -> {Collections.sort(l);}
		);
	}
	
	/**
	 * identique a test1 mais ecrit d'une maniere differente
	 */
	public static void test2() {
		withTimeForAlgoOnList(
			LinkedList<Integer>::new, 
			listInitialiser(100, 0),  
			Collections::sort
		);
			
		withTimeForAlgoOnList(
			ArrayList<Integer>::new, 
			listInitialiser(100, 0),  
			Collections::sort
		);
	}
	
	public static void main(String[] args) {

	}
	
}
