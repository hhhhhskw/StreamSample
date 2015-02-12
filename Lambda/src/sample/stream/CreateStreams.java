package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStreams {

	private static void of(String[] simpleArray) {
		Stream<String> stream = Stream.of(simpleArray);
		System.out.println(stream.collect(Collectors.joining()));
	}

	private static void empty(List<String> randList) {
		List<Integer> result = randList.stream().flatMap(s -> {
			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.err.println(s);
				return Stream.empty();
			}
			return Stream.of(i);
		}).collect(Collectors.toList());
		System.out.println(result);
	}

	private static void generate(int i) {
		// java.security.SecureRandom
		// DoubleStream ds = new SecureRandom().doubles().limit(i).map(arg -> {
		// String str = String.format("%.3f", arg);
		// return Double.parseDouble(str);
		// });
		// List<Double> dslist = ds.boxed().collect(Collectors.toList());
		// System.out.println(dslist);

		// java.lang.Math#random
		DoubleStream stream = DoubleStream.generate(Math::random).map(arg -> {
			String str = String.format("%.3f", arg);
			return Double.parseDouble(str);
		});
		List<Double> list = stream.limit(i).boxed().collect(Collectors.toList());
		System.out.println(list);
	}

	private static void iterate(int i) {
		IntStream is = IntStream.iterate(1, t -> t*10);
		Stream<Integer> stream = is.limit(i).boxed();
		System.out.println(stream.collect(Collectors.toList()));
	}

	public static void main(String[] args) {

		String[] simpleArray = { "a", "bb", "ccc" };
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");

		// �z��(String)��Stream�ɕϊ�����
		of(simpleArray);

		// ���X�g���̒l�𐔒l�ɕϊ�����(���l�ȊO�̒l�̏ꍇ�͋��Stream��ԋp����)
		empty(randList);

		// �����_����double�l�𖳌��ɏo�͂���Stream�𐶐����A�w�肵�����̃��X�g���o�͂���
		generate(10);

		// �ݏ�𐶐����A�w�肵�����̃��X�g���o�͂���
		iterate(10);
	}


}
