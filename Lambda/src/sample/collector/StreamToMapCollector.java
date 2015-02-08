package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamToMapCollector {

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
	
	public static void main(String[] args) {
		List<String> kvList = Arrays.asList("key1-val1", "key2-val2");

		// Map‚É•ÏŠ·‚·‚é
		toMap(kvList);
		
		// ConcurrentHashMap‚É•ÏŠ·‚·‚é
		toConcurrentMap(kvList);
	}
}
