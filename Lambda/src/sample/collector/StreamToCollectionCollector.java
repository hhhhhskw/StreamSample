package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToCollectionCollector {

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

	public static void main(String[] args) {
		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		String[] simpleArray = {"a", "bb", "ccc"};
		
		// Collection(‚±‚±‚Å‚ÍQueue)‚Ö•ÏŠ·‚·‚é
		toCollection(simpleList);
		
		// List‚Ö•ÏŠ·‚·‚é
		toList(simpleArray);
		
		// Set‚Ö•ÏŠ·‚·‚é
		toSet(simpleList);
	}
}
