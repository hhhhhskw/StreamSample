package sample.collector;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummaryCollector {

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
		String[] intArray = {"22", "1", "333"};
		String[] doubleArray = {"1e2", "3e1", "5"};

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

		// 要素内の各値をintに変換して各種集計値を算出する。
		summarizingInt(intArray);
		
		// 要素内の各値をlongに変換して各種集計値を算出する。
		summarizingLong(intArray);
		
		// 要素内の各値をdoubleに変換して各種集計値を算出する。
		summarizingDouble(doubleArray);
	}
}
