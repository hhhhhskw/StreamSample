package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GeneralCollector {

	private static void mapping(List<String> simpleList) {
		String str = simpleList.stream().collect(Collectors.mapping(a -> a.toUpperCase(), Collectors.joining("|")));
		System.out.println(str);
	}
	
	private static void reduceVal(List<String> simpleList) {
		Optional<String> qw = simpleList.stream().collect(Collectors.reducing((q, w) -> q + w));
		System.out.println(qw);
	}
	
	private static void reduceValWithDefaultVal(List<String> simpleList) {
		String str = simpleList.stream().collect(Collectors.reducing("xyz | ", (i, g) -> i + g));
		System.out.println(str);
	}
	
	private static void reduceValWithDefaultValAndDefineMappingRule(List<String> strList) {
		Integer result = strList.stream().collect(Collectors.reducing(-5, i -> i.length(), (g, a) -> g + a));
		System.out.println(result); // 1
	}
	
	public static void main(String[] args) {
		List<String> simpleList = Arrays.asList("a", "bb", "ccc");

		// 値を変換して他のCollectorを呼び出す
		mapping(simpleList);

		// 値を集約する (※戻り値はOptional型)
		reduceVal(simpleList);

		// 値を集約する(初期値の指定あり)(※戻り値はString型)
		reduceValWithDefaultVal(simpleList);

		// 値を集約する(初期値、および値の集約ルールの指定あり)(※戻り値の型は値の集約ルールによって変わる)
		reduceValWithDefaultValAndDefineMappingRule(simpleList);
	}
}
