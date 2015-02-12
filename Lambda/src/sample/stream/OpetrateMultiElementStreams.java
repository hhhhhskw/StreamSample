package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpetrateMultiElementStreams {

	private static void reduce(List<String> randList) {
		Optional<String> str = randList.stream().reduce((f,g) -> f + g);
		System.out.println(str);
	}

	private static void collect(List<String> randList) {
		StringBuilder sb  = randList.stream().collect(
				StringBuilder::new,
				(a,b) -> a.append(b),
				(a1,b1) -> a1.append(b1));
		System.out.println(sb.toString());
	}

	private static void concat(List<String> simpleList, List<String> intList) {
		Stream<String> stream = Stream.concat(simpleList.stream(), intList.stream());
		System.out.println(stream.collect(Collectors.toList()));		
	}

	public static void main(String[] args) {

		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");
		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		List<String> intList = Arrays.asList("1","22","333");

		// リストの値を集約(reduce)する
		reduce(randList);
		
		// リストの値を使用して任意の結果を作成する
		collect(randList);
		
		// 2つのリストを結合する
		concat(simpleList, intList);
	}
}
