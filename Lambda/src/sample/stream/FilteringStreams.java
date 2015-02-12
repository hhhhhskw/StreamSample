package sample.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilteringStreams {

	private static void filter(List<String> simpleList) {
		List<String> filteredList = simpleList.stream()
				.filter(s -> s.length() == 2)
				.collect(Collectors.toList());
		System.out.println(filteredList);
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

	private static void distinct(List<String> randList) {
		List<String> distinctedList = randList.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println(distinctedList);
		
	}

	public static void main(String[] args) {

		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");
		List<String> intList = Arrays.asList("1","333","22");

		// ���X�g�Ɋ܂܂��l�̃t�B���^�����O
		filter(simpleList);

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

		// ���X�g�Ɋ܂܂��l�̏d����r������
		distinct(randList);
	}
}
