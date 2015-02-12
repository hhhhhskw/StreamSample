package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperateStreams {

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

	public static void main(String[] args) {

		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");

		// ����ΏۂƂ���l�̌��𐧌�(limit)����
		limit(randList);
			
		// ����ΏۂƂ���l�̊J�n�ʒu��ύX(skip)����
		skip(randList);
	}
}
