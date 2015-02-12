package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortStreams {

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

	public static void main(String[] args) {

		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");
		
		// ���X�g�Ɋ܂܂��l���\�[�g����
		sort(randList);
		
		// ���X�g�Ɋ܂܂��l���\�[�g����i�\�[�g����Comparator�̎����Ɉˑ�����j
		sortWithComparator(randList);
	}
}
