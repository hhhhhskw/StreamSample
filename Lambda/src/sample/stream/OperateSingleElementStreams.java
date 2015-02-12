package sample.stream;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class OperateSingleElementStreams {

	private static void map(List<String> pathList) {
		List<Path> mappedList = pathList.stream().map(Paths::get)
				.collect(Collectors.toList());
		System.out.println(mappedList);
	}

	private static void mapToDouble(List<String> doubleList) {
		OptionalDouble before = doubleList.stream()
				.mapToDouble(Double::parseDouble).average();
		double after = new BigDecimal(String.valueOf(before.getAsDouble()))
				.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
		System.out.println(after);
	}

	private static void mapToInt(List<String> simpleList) {
		int sum = simpleList.stream().mapToInt(s -> s.length()).sum();
		System.out.println(sum);
	}

	private static void mapToLong(List<String> longList) {
		long l = longList.stream().mapToLong(Long::parseLong).sum();
		System.out.println(l);
	}

	private static void flatMap(List<String> numList) {
		List<String> result = numList.stream().flatMap(s -> {
			String target = s.substring(0, 1);
			int n = Integer.parseInt(s.substring(1));

			String[] array = new String[n];
			Arrays.fill(array, target);

			return Stream.of(array);
		}).collect(Collectors.toList());
		System.out.println(result);
	}

	private static String flatMapToDouble(List<String> doubleList) {
		DoubleStream ds = doubleList.stream().flatMapToDouble(arg -> {
			return DoubleStream.of(Double.parseDouble(arg));
		});
		OptionalDouble result = ds.average();
		String formattedResult = String.format("%.2f", result.getAsDouble());
		System.out.println(formattedResult);
		return formattedResult;
	}

	private static void flatMapToInt(List<String> numList) {
		IntStream is = numList.stream().flatMapToInt(arg -> {
			int i = Integer.parseInt(arg.substring(1, 2));
			return IntStream.of(i);
		});
		System.out.println(is.sum());
	}

	private static void flatMapToLong(List<String> longList) {
		LongStream ls = longList.stream().flatMapToLong(arg -> {
			return LongStream.of(Long.parseLong(arg));
		});
		long result = ls.sum();
		System.out.println(result);
	}

	private static void forEach(List<String> doubleList) {

		doubleList.stream().forEach(num -> {
			double d = Double.parseDouble(num);
			int i = (int) d;
			System.out.println(i);
		});
	}

	private static void forEachOrdered(List<String> longList) {
		StringBuilder sb = new StringBuilder();
		longList.stream().forEachOrdered(l -> {
			sb.append(l);
		});
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {

		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		List<String> pathList = Arrays.asList("a.txt", "b.txt", "c.txt");
		List<String> numList = Arrays.asList("a1", "b2", "c3");
		List<String> doubleList = Arrays.asList("1.111", "22.22", "333.3");
		List<String> longList = Arrays.asList("11111", "222", "3333333");

		// ���X�g�Ɋ܂܂��l�̕ύX
		map(pathList);

		// ���X�g�Ɋ܂܂��l�̕���(double)�����߂�
		mapToDouble(doubleList);

		// ���X�g�Ɋ܂܂��l�̌������߂�
		mapToInt(simpleList);

		// ���X�g�Ɋ܂܂��l�̑��a(long)�����߂�
		mapToLong(longList);

		// ���X�g�Ɋ܂܂��l�𕪊�����
		flatMap(numList);

		// ���X�g�Ɋ܂܂��l�̕���(double)�����߂�
		flatMapToDouble(doubleList);

		// ���X�g�Ɋ܂܂��l�Ɋ܂܂�鐔�l(int)���擾�����Z����
		flatMapToInt(numList);

		// ���X�g�Ɋ܂܂��l�̑��a(long)�����߂�
		flatMapToLong(longList);

		// ���X�g�Ɋ܂܂��e�l(double)�̏����_�ȉ���؂�̂ďo�͂���
		forEach(doubleList);
		
		// ���X�g�Ɋ܂܂��e�l(long)��擪���珇�ɂȂ��ďo�͂���
		forEachOrdered(longList);
		
	}
}
