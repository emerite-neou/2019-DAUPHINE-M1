package fr.dauphine.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StreamExample {
	
	/* PARTIE STREAM */
	public Stream<String> toUpperCase(Stream<String> stream) {
		return stream.map(s -> {return s.toUpperCase();});
	}
	
	public Stream<String> toUpperCaseWithM(Stream<String> stream) {
		return stream
			.map(s -> {return s.toUpperCase();})
			.filter(s-> {return s.contains("M");})
		;
	}
	
	public void print(Stream<String> stream) {
		stream.forEach(s -> {System.out.println(s);});
	}	
	
	/* PARTIE ENTRAINEMENT */
	public Stream<Integer> toLength(Stream<String> stream) {
		return stream.map(s -> {return s.length();});
	}	
	
	public static int score (String s) {
		int res = 0;
		for ( int i = 0 ; i < s.length() ; ++i ) {
			char c = s.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				res += 2;
			} else {
				res +=1;
			}
		}
		return res;
	}
	
	public static int score2 (String s, int iDouble) {
		int res = 0;
		for ( int i = 0 ; i < s.length() ; ++i ) {
			char c = s.charAt(i);
			int score = 0;
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				score = 2;
			} else {
				score =1;
			}
			
			if (iDouble == i) score *= 2;
			
			res += score;
		}
		return res;
	}		
	
	public Stream<Integer> toScore(Stream<String> stream) {
		return stream.map(StreamExample::score);
	}
	
	public Stream<Map<String, Object>> toInfo(Stream<String> stream) {
		return stream
				.map((String word) -> {
					Map<String, Object> map = new HashMap<>();
					map.put("word", word);
					return map;
				})
				.map((map) -> {
					String word = (String) map.get("word");
					map.put("score", score(word));
					return map;
				})
				.map((map) -> {
					String word = (String) map.get("word");
					map.put("size", word.length());
					return map;
				})
				.filter(map -> {
					int score = (Integer) map.get("score");
					return score >= 10;
				})
	
				.map(map-> {
					String word = (String) map.get("word");
					List<Integer> scores = new LinkedList<>();
					for ( int i = 0; i < word.length() ; ++i ) {
						scores.add(score2(word, i));
					}
					map.put("scores", scores);
					return map;
				})
				
				.map(map-> {
					List<Integer> scores = (List<Integer>) map.get("score");
					map.put("score_min", Collections.min(scores));
					return map;
				})
				
				.map(map-> {
					List<Integer> scores = (List<Integer>) map.get("score");
					map.put("score_max", Collections.max(scores));
					return map;
				})
				
				.map(map-> {
					List<Integer> scores = (List<Integer>) map.get("score");
					int sum = 0;
					for (Integer v : scores) {
						sum += v;
					}
					map.put("score_avg", sum/scores.size());
					return map;
				})
		;
	}
	
	/*PARTIE POINT*/
	public static String toCentroid(Stream<String> points) {
		Map<String, Object> pointsAcc = points
				.map(word -> {
					Map<String, Object> map = new HashMap<>();
					map.put("pointString", word);
					return map;
				})
				
				.map(map -> {
					String pointString = (String) map.get("pointString");
					map.put("x", Integer.parseInt(pointString.split(",")[0]));
					return map;
				})
				
				.map(map -> {
					String pointString = (String) map.get("pointString");
					map.put("y", Integer.parseInt(pointString.split(",")[1]));
					return map;
				})
				
				.collect(
						HashMap::new,
						(acc, point) -> {
							if ( acc.containsKey("n") ) {
								int xpoint = (int) point.get("x");
								int ypoint = (int) point.get("y");
								
								int xacc = (int) acc.get("x");
								int yacc = (int) acc.get("y");
								int nacc = (int) acc.get("n");
								
								acc.put("x", xpoint+xacc);
								acc.put("y", ypoint+yacc);
								acc.put("n", nacc+1);
							} else {
								int xpoint = (int) point.get("x");
								int ypoint = (int) point.get("y");
								
								acc.put("x", xpoint);
								acc.put("y", ypoint);
								acc.put("n", 1);
							}
						},
						(acc1, acc2) -> {
							int xacc1 = (int) acc1.get("x");
							int yacc1 = (int) acc1.get("y");
							int nacc1 = (int) acc1.get("n");
							
							int xacc2 = (int) acc2.get("x");
							int yacc2 = (int) acc2.get("y");
							int nacc2 = (int) acc2.get("n");
							
							acc1.put("x", xacc1+xacc2);
							acc1.put("y", yacc2+yacc2);
							acc1.put("n", nacc1+nacc2);
						}
				)
		;
		
		float x = (int) pointsAcc.get("x");
		float y = (int) pointsAcc.get("y");
		float n = (int) pointsAcc.get("n");
		
		
		float xcentroid = x/n;
		float ycentroid = y/n;
		
		return xcentroid+"_"+ycentroid;
	}
	
}
