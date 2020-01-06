package fr.dauphine.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Reducer {

	/**
	 * 
	 * @param <E> immutable
	 * @param <R>
	 * @param l
	 * @param identity
	 * @param accumulator
	 * @return
	 */
	public static <E, R> R reduce(
			List<E> l, 
			R identity, 
			BiFunction<R, E, R> accumulator 
			) {
		R res = identity;
		for (E val : l) {
			res = accumulator.apply(res, val);
		}
		return res;
		
	}
	
	public static final ExecutorService EXECUTION_CONTEXT = Executors.newFixedThreadPool(4);
	public static int N = 1024;
	
	/**
	 * 
	 * @param <E> immutable
	 * @param <R>
	 * @param l
	 * @param identity
	 * @param accumulator
	 * @param combiner
	 * @return
	 */
	public static <E, R> R reduceParallel(
			List<E> l, 
			R identity, 
			BiFunction<R, E, R> accumulator,
			BinaryOperator<R> combiner
			) {
		
		// submit all tasks
		List<Future<R>> futurs = new LinkedList<>();
		for (int i = 0; i < l.size(); i += N) {
			int end = i+N;
			if ( end >= l.size() ) end = l.size()-1;
			final int finali = i;
			final int finalEnd = end;
			futurs.add(EXECUTION_CONTEXT.submit(() -> {
				R acc = identity;
				for (int j = finali; j < finalEnd; j++) {
					acc = accumulator.apply(acc, l.get(j));
				}
				return acc;
			}));
		}
		
		// await termination of all tasks and get the values
		List<R> accs = futurs.stream()
			.map(res-> {
				try {
					return res.get();
				} catch (InterruptedException | ExecutionException e) {
					//DO SOMETHING SMART
					return identity;
				}
			})
			.collect(Collectors.toList())
		;
		
		// combine all the values of all the tasks
		R res = identity;
		for (R currVal : accs) {
			res = combiner.apply(res, currVal);
		}		
		
		return res;
	}
}
