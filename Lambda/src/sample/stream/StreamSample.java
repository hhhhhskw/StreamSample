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
		
		// ���X�g�Ɋ܂܂��l�̃t�B���^�����O
		filter(simpleList);

		// ���X�g�Ɋ܂܂��l�̕ύX
		map(pathList);

		// ���X�g�Ɋ܂܂��l�̌������߂�
		mapToInt(simpleList);

		// ���X�g�Ɋ܂܂��l�𕪊�����
		floatMap(numList);
		
		// ���X�g�Ɋ܂܂��l�̏d����r������
		distinct(randList);
		
		// ���X�g�Ɋ܂܂��l���\�[�g����
		sort(randList);
		
		// ���X�g�Ɋ܂܂��l���\�[�g����i�\�[�g����Comparator�̎����Ɉˑ�����j
		sortWithComparator(randList);
		
		// ���X�g�Ɋ܂܂��l�ɑ΂��鑀�쒆�ɕ���p���܂ޏ������s���i�������ł̓��O�o�́j
		peek(randList);
		
		// ����ΏۂƂ���l�̌��𐧌�(limit)����
		limit(randList);
			
		// ����ΏۂƂ���l�̊J�n�ʒu��ύX(skip)����
		skip(randList);
		
		// ���X�g(String)��z��(String)�ɕϊ�����
		toArray(randList);
		
		// ���X�g�̒l���W��(reduce)����
		reduce(randList);
		
		// ���X�g�̒l���g�p���ĔC�ӂ̌��ʂ��쐬����
		collect(randList);
		
		// ���X�g�Ɋ܂܂��l�̂����A�ŏ��l��ԋp����
		min(randList);
		
		// ���X�g�Ɋ܂܂��l�̂����A�ő�l��ԋp����
		max(randList);
		
		// ���X�g�Ɋ܂܂��v�f�̐���ԋp����
		count(randList);
		
		// ���X�g�Ɋ܂܂��v�f�ɑ΂��āA��������(������1�v�f�ł����v�����true)���s��
		anyMatch(randList);
		
		// ���X�g�Ɋ܂܂��v�f�ɑ΂��āA��������(�����Ɋ��S�Ɉ�v����ꍇ��true)���s��
		allMatch(intList, randList);
		
		// ���X�g�Ɋ܂܂��v�f�ɑ΂��āA��������(������v����l���Ȃ����true)���s��
		noneMatch(intList, randList);
		
		// ���X�g�Ɋ܂܂��v�f�̐擪�̒l��ԋp����
		findFirst(randList);
		
		// ���X�g�Ɋ܂܂��v�f�̂����ꂩ�̒l��ԋp����
		findAny(randList);
	}
}
