package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingCollector {

	private static void groupingBy(String[] simpleArray) {
		Map<Integer, List<String>> result = Stream.of(simpleArray).collect(Collectors.groupingBy(i -> i.length()));
		System.out.println(result);
	}
	
	private static void groupingByRule(List<String> randList) {
		Map<Object,String> s = randList.stream().collect(Collectors.groupingBy(r -> r.length(), Collectors.joining(":")));
		System.out.println(s);
		System.out.println(s.getClass());		
	}
	
	private static void groupingByRuleAndDefiningReturnType(List<String> randList) {
		Map<Object,String> s = randList.stream().collect(Collectors.groupingBy(r -> r.length(), ConcurrentHashMap::new, Collectors.joining(":")));
		System.out.println(s);	
		System.out.println(s.getClass());		
	}
	
	private static void groupingByConcurrent(List<String> randList) {
		Map<Integer, List<String>> result = randList.stream().collect(Collectors.groupingByConcurrent(i -> i.length()));
		System.out.println(result);
		System.out.println(result.getClass());
	}
	
	public static void main(String[] args) {
		String[] simpleArray = {"a", "bb", "ccc"};
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");


		// 要素内の値をグループ分けする
		groupingBy(simpleArray);
		
		// 要素内の値をグループ分けし、指定した巻数で集約する
		groupingByRule(randList);
		
		// 要素内の値をグループ分けし、指定した巻数で集約し、指定した型で値を返却する
		groupingByRuleAndDefiningReturnType(randList);

		// 要素内の値をグループ分けし、ConcurrentHashMapにて返却される
		groupingByConcurrent(randList);
	}
}
