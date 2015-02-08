package sample.collector;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorSample {

	private static void toCollection(List<String> simpleList) {
		Stream<String> s = simpleList.stream();
		Queue<String> queue = s.collect(Collectors.toCollection(LinkedBlockingQueue::new));
		System.out.println(queue);
	}

	private static void toList(String[] simpleArray) {
		List<String> list = Stream.of(simpleArray).collect(Collectors.toList());
		System.out.println(list);
	}

	private static void toSet(List<String> simpleList) {
		Set<String> set = simpleList.stream().collect(Collectors.toSet());
		System.out.println(set);
	}

	private static void joining(List<String> simpleList) {
		String s = simpleList.stream().collect(Collectors.joining());
		System.out.println(s);
	}
	
	private static void joiningWithDelimiter(List<String> intList) {
		String s = intList.stream().collect(Collectors.joining("-"));
		System.out.println(s);
	}
	
	private static void joiningWithDelimiterAndEncloser(List<String> urlList) {
		String url = urlList.stream().collect(Collectors.joining(".", "http://", "/"));
		System.out.println(url);
	}
	
	private static void mapping(List<String> simpleList) {
		String str = simpleList.stream().collect(Collectors.mapping(a -> a.toUpperCase(), Collectors.joining("|")));
		System.out.println(str);
	}
	
	private static void collectingAndThen(String[] simpleArray) {
		Stream<String> s = Arrays.stream(simpleArray);
		List<String> list = s.collect(Collectors.collectingAndThen(
				Collectors.toList(), g -> Collections.unmodifiableList(g)));
		System.out.println(list);
	}
	
	private static void counting(List<String> simpleList) {
		long l = simpleList.stream().collect(Collectors.counting());
		System.out.println(l);
	}
	
	private static void minBy(String[] numArray) {
		Stream<String> s = Stream.of(numArray);
		Optional<String> min = s.collect(Collectors.minBy(Comparator.naturalOrder()));
		System.out.println(min);
	}
	
	private static void maxBy(String[] numArray) {
		Stream<String> s = Arrays.stream(numArray);
		Optional<String> max = s.collect(Collectors.maxBy(Comparator.naturalOrder()));
		System.out.println(max);
	}
	
	private static void summingInt(String[] numArray) {
		int i = Stream.of(numArray).collect(Collectors.summingInt(Integer::parseInt));
		System.out.println(i);
	}
	
	private static void summingLong(String[] numArray) {
		long l = Stream.of(numArray).collect(Collectors.summingLong(p -> Long.parseLong(p)));
		System.out.println(l);
	}
	
	private static void summingDouble(String[] doubleArray) {
		double d = Arrays.stream(doubleArray).collect(Collectors.summingDouble(Double::parseDouble));
		System.out.println(d);
	}
	
	private static void averagingInt(String[] intArray) {
		double i = Stream.of(intArray).collect(Collectors.averagingInt(Integer::parseInt));
		String after = String.format("%.2f", i);
		System.out.println(after);
	}
	
	private static void averagingLong(String[] intArray) {
		double d = Arrays.stream(intArray).collect(Collectors.averagingLong(w -> Long.parseLong(w)));
		double after = new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(after);
	}
	
	private static void averagingDouble(String[] doubleArray) {
		double d = Arrays.stream(doubleArray).collect(Collectors.averagingDouble(j -> Double.parseDouble(j)));
		System.out.println(d);
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
	
	private static void partitioningBy(List<String> randList) {
		Map<Boolean, List<String>> map =randList.stream().collect(Collectors.partitioningBy(i -> i.length() == 3));
		System.out.println(map);
	}
	
	private static void partitioningByRule(List<String> randList) {
    	Pattern p = Pattern.compile("^[0-9]*$");
    	Stream<String> stream = randList.stream();
		Map<Boolean, String> map = stream.collect(Collectors.partitioningBy(a -> p.matcher(a).find(), Collectors.mapping(s -> s.toString(), Collectors.joining())));
		System.out.println(map);
	}
	
	private static void toMap(List<String> kvList) {
		Map<String, String> map = kvList.stream().collect(Collectors.toMap(
			t -> t.split("-")[0],
			t -> t.split("-")[1],
			(v1, v2) -> v1 + ":" + v2
			)
		);
		System.out.println(map);
		System.out.println(map.getClass());
	}
	
	private static void toConcurrentMap(List<String> kvList) {
		Map<String, String> map = kvList.stream().collect(Collectors.toConcurrentMap(
			t -> t.split("-")[0],
			t -> t.split("-")[1],
			(v1, v2) -> v1 + ":" + v2
			)
		);
		System.out.println(map);
		System.out.println(map.getClass());
	}
	
	private static void summarizingInt(String[] intArray) {
		IntSummaryStatistics i = Stream.of(intArray).collect(Collectors.summarizingInt(Integer::parseInt));
		System.out.println(i);
		System.out.println("AVG : " + i.getAverage());
		System.out.println("SUM : " + i.getSum());
		System.out.println("COUNT : " + i.getCount());
		System.out.println("MAX : " + i.getMax());
		System.out.println("MIN : " + i.getMin());
	}

	private static void summarizingLong(String[] intArray) {
		LongSummaryStatistics l = Stream.of(intArray).collect(Collectors.summarizingLong(Long::parseLong));
		System.out.println(l);
		System.out.println("AVG : " + l.getAverage());
		System.out.println("SUM : " + l.getSum());
		System.out.println("COUNT : " + l.getCount());
		System.out.println("MAX : " + l.getMax());
		System.out.println("MIN : " + l.getMin());
	}
	
	private static void summarizingDouble(String[] doubleArray) {
		DoubleSummaryStatistics d = Stream.of(doubleArray).collect(Collectors.summarizingDouble(Double::parseDouble));
		System.out.println(d);
		System.out.println("AVG : " + d.getAverage());
		System.out.println("SUM : " + d.getSum());
		System.out.println("COUNT : " + d.getCount());
		System.out.println("MAX : " + d.getMax());
		System.out.println("MIN : " + d.getMin());
	}
	
	public static void main(String[] args) {
		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		String[] simpleArray = {"a", "bb", "ccc"};
		List<String> telNoList = Arrays.asList("090","0000","9999");
		List<String> urlList = Arrays.asList("www", "google", "co", "jp");
		String[] intArray = {"22", "1", "333"};
		String[] doubleArray = {"1e2", "3e1", "5"};
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");
		List<String> kvList = Arrays.asList("key1-val1", "key2-val2");


		// Collection(ここではQueue)へ変換する
		toCollection(simpleList);
		
		// Listへ変換する
		toList(simpleArray);
		
		// Setへ変換する
		toSet(simpleList);
		
		// 結合してStringに変換する
		joining(simpleList);
		
		// 結合してStringに変換する（区切り文字あり）
		joiningWithDelimiter(telNoList);
		
		// 結合してStringに変換する（区切り文字・始終端文字あり）
		joiningWithDelimiterAndEncloser(urlList);
		
		// 値を変換して他のCollectorを呼び出す
		mapping(simpleList);
		
		// 他のCollctorが呼び出した結果を変換する
		collectingAndThen(simpleArray);
		
		// 要素数を取得する
		counting(simpleList);
		
		// 最小値を取得する(※mappingを使っても同様の処理はかけるが面倒)
		minBy(intArray);
		
		// 最大値を取得する(※mappingを使っても同様の処理はかけるが面倒)
		maxBy(intArray);
		
		// 要素内の各値をintに変換し、合算する。
		summingInt(intArray);
		
		// 要素内の各値をlongに変換し、合算する。
		summingLong(intArray);
		
		// 要素内の各値をdoubleに変換し、合算する。
		summingDouble(doubleArray);
		
		// 要素内の各値をintに変換し、平均値を算出する。(※戻り値はdouble型)
		averagingInt(intArray);
		
		// 要素内の各値をlongに変換し、平均値を算出する。(※戻り値はdouble型)
		averagingLong(intArray);
		
		// 要素内の各値をdoubleに変換し、平均値を算出する。(※戻り値はdouble型)
		averagingDouble(doubleArray);		
		
		// 値を集約する (※戻り値はOptional型)
		reduceVal(simpleList);
		
		// 値を集約する(初期値の指定あり)(※戻り値はString型)
		reduceValWithDefaultVal(simpleList);
		
		// 値を集約する(初期値、および値の集約ルールの指定あり)(※戻り値の型は値の集約ルールによって変わる)
		reduceValWithDefaultValAndDefineMappingRule(simpleList);
		
		// 要素内の値をグループ分けする
		groupingBy(simpleArray);
		
		// 要素内の値をグループ分けし、指定した巻数で集約する
		groupingByRule(randList);
		
		// 要素内の値をグループ分けし、指定した巻数で集約し、指定した型で値を返却する
		groupingByRuleAndDefiningReturnType(randList);

		// 要素内の値をグループ分けし、ConcurrentHashMapにて返却される
		groupingByConcurrent(randList);
		
		// 条件を満たすグループと満たさないグループに分ける
		partitioningBy(randList);
		
		// 条件を満たすグループと満たさないグループに分け、指定した巻数で集約する
		partitioningByRule(randList);

		// Mapに変換する
		toMap(kvList);
		
		// ConcurrentHashMapに変換する
		toConcurrentMap(kvList);
		
		// 要素内の各値をintに変換して各種集計値を算出する。
		summarizingInt(intArray);
		
		// 要素内の各値をlongに変換して各種集計値を算出する。
		summarizingLong(intArray);
		
		// 要素内の各値をdoubleに変換して各種集計値を算出する。
		summarizingDouble(doubleArray);
	}
}
