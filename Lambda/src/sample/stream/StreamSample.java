package sample.stream;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSample {

	private static void filter(List<String> simpleList) {
		List<String> filteredList = simpleList.stream()
				.filter(s -> s.length() == 2)
				.collect(Collectors.toList());
		System.out.println(filteredList);
	}

	private static void map(List<String> pathList) {
		List<Path> mappedList = pathList.stream()
				.map(Paths::get)
				.collect(Collectors.toList());
		System.out.println(mappedList);
	}

	private static void mapToInt(List<String> simpleList) {
		int sum = simpleList.stream()
				.mapToInt(s -> s.length())
				.sum();
		System.out.println(sum);
	}

	private static void floatMap(List<String> numList) {
		List<String> result = numList.stream()
				.flatMap(s -> {
					String target = s.substring(0, 1);
					int n = Integer.parseInt(s.substring(1));
		
					String[] array = new String[n];
					Arrays.fill(array, target);
		
					return Stream.of(array);
				})
				.collect(Collectors.toList());
		System.out.println(result);
	}

	private static void distinct(List<String> randList) {
		List<String> distinctedList = randList.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println(distinctedList);
		
	}

	private static void sort(List<String> randList) {
		List<String> sortedList = randList.stream()
				.sorted()
				.collect(Collectors.toList());
		System.out.println(sortedList);
	}

	private static void sortWithComparator(List<String> randList) {
		List<String> sortedList = randList.stream()
				.sorted((s1, s2) -> s1.length() - s2.length())
				.collect(Collectors.toList());
		System.out.println(sortedList);
	}

	private static void peek(List<String> randList) {
		Logger logger = Logger.getLogger(StreamSample.class.getName());
		randList.stream()
				.peek(t -> logger.log(Level.FINE, t))
				.map(t -> t + "|" +  t)
				.peek(t -> logger.log(Level.FINE, t))
				.forEach(System.out::println);
	}

	private static void limit(List<String> randList) {
		List<String> limitedList = randList.stream()
				.limit(4)
				.collect(Collectors.toList());
		System.out.println(limitedList);
	}

	private static void skip(List<String> randList) {
		List<String> skippedList = randList.stream()
				.skip(3)
				.collect(Collectors.toList());
		System.out.println(skippedList);
	}

	private static void toArray(List<String> randList) {
		String[] array = randList.stream().toArray(String[]::new);
		System.out.println(Arrays.toString(array));
	}

	private static void reduce(List<String> randList) {
		Optional<String> str = randList.stream().reduce((f,g) -> f + g);
		System.out.println(str);
		System.out.println(str.get());
	}

	private static void collect(List<String> randList) {
		StringBuilder sb  = randList.stream().collect(
				StringBuilder::new,
				(a,b) -> a.append(b),
				(a1,b1) -> a1.append(b1));
		System.out.println(sb.toString());
	}

	private static void min(List<String> randList) {
		Optional<String> min =  randList.stream().min(Comparator.naturalOrder());
		System.out.println(min);
	}

	private static void max(List<String> randList) {
		Optional<String> max = randList.stream().max(Comparator.naturalOrder());
		System.out.println(max);
	}

	private static void count(List<String> randList) {
		long count = randList.stream().count();
		System.out.println(count);
	}

	private static void anyMatch(List<String> randList) {
		boolean first = randList.stream().anyMatch(p -> p.length() == 3);
		System.out.println(first);
		boolean second = randList.stream().anyMatch(q -> q.length() == 1000);
		System.out.println(second);
	}

	private static void allMatch(List<String> intList, List<String> randList) {
    	Pattern p = Pattern.compile("^[0-9]*$");

		boolean first = intList.stream().allMatch(a -> p.matcher(a).find());
		System.out.println(first);
		boolean second = randList.stream().allMatch(b -> p.matcher(b).find());
		System.out.println(second);
	}

	private static void noneMatch(List<String> intList, List<String> randList) {
    	Pattern p = Pattern.compile("^[a-z]*$");

		boolean first = intList.stream().noneMatch(a -> p.matcher(a).find());
		System.out.println(first);
		boolean second = randList.stream().noneMatch(z -> p.matcher(z).find());
		System.out.println(second);
	}

	private static void findFirst(List<String> randList) {
		Optional<String> result = randList.stream().findFirst();
		System.out.println(result);
	}

	private static void findAny(List<String> randList) {
		Optional<String> result = randList.stream().findAny();
		System.out.println(result);
	}


	public static void main(String[] args) {

		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		List<String> pathList = Arrays.asList("a.txt", "b.txt", "c.txt");
		List<String> numList = Arrays.asList("a1", "b2", "c3");
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");
		List<String> intList = Arrays.asList("1","333","22");
		
		// リストに含まれる値のフィルタリング
		filter(simpleList);

		// リストに含まれる値の変更
		map(pathList);

		// リストに含まれる値の個数を求める
		mapToInt(simpleList);

		// リストに含まれる値を分割する
		floatMap(numList);
		
		// リストに含まれる値の重複を排除する
		distinct(randList);
		
		// リストに含まれる値をソートする
		sort(randList);
		
		// リストに含まれる値をソートする（ソート順はComparatorの実装に依存する）
		sortWithComparator(randList);
		
		// リストに含まれる値に対する操作中に副作用を含む処理を行う（※ここではログ出力）
		peek(randList);
		
		// 操作対象とする値の個数を制限(limit)する
		limit(randList);
			
		// 操作対象とする値の開始位置を変更(skip)する
		skip(randList);
		
		// リスト(String)を配列(String)に変換する
		toArray(randList);
		
		// リストの値を集約(reduce)する
		reduce(randList);
		
		// リストの値を使用して任意の結果を作成する
		collect(randList);
		
		// リストに含まれる値のうち、最小値を返却する
		min(randList);
		
		// リストに含まれる値のうち、最大値を返却する
		max(randList);
		
		// リストに含まれる要素の数を返却する
		count(randList);
		
		// リストに含まれる要素に対して、条件検索(条件に1要素でも合致すればtrue)を行う
		anyMatch(randList);
		
		// リストに含まれる要素に対して、条件検索(条件に完全に一致する場合はtrue)を行う
		allMatch(intList, randList);
		
		// リストに含まれる要素に対して、条件検索(条件一致する値がなけれはtrue)を行う
		noneMatch(intList, randList);
		
		// リストに含まれる要素の先頭の値を返却する
		findFirst(randList);
		
		// リストに含まれる要素のいずれかの値を返却する
		findAny(randList);
	}
}
