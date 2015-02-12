package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugStream {

	private static void peek(List<String> randList) {
		Logger logger = Logger.getLogger(DebugStream.class.getName());
		randList.stream()
				.peek(t -> logger.log(Level.INFO, t))
				.map(t -> t + "|" +  t)
				.peek(t -> logger.log(Level.INFO, t))
				.forEach(System.out::println);
	}

	public static void main(String[] args) {

		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");

		// リストに含まれる値に対する操作中に副作用を含む処理を行う（※ここではログ出力）
		peek(randList);
	}
}
