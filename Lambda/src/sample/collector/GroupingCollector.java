package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingCollector {

	private static void groupingBy(String[] simpleArray) {
		Map<Integer, List<String>> result = Stream.of(simpleArray).collect(Collectors.groupingBy(i -> i.length()));
		System.out.println(result);
	}
	
	private static void groupingByRule(List<String> randList) {
		Map<Object,String> s = randList.stream().collect(Collectors.groupingBy(r -> r.length(), Collectors.joining(":")));
		System.out.println(s);
		System.out.println(s.getClass());		
	}
	
	private static void groupingByRuleAndDefiningReturnType(List<String> randList) {
		Map<Object,String> s = randList.stream().collect(Collectors.groupingBy(r -> r.length(), ConcurrentHashMap::new, Collectors.joining(":")));
		System.out.println(s);	
		System.out.println(s.getClass());		
	}
	
	private static void groupingByConcurrent(List<String> randList) {
		Map<Integer, List<String>> result = randList.stream().collect(Collectors.groupingByConcurrent(i -> i.length()));
		System.out.println(result);
		System.out.println(result.getClass());
	}
	
	public static void main(String[] args) {
		String[] simpleArray = {"a", "bb", "ccc"};
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");


		// �v�f���̒l���O���[�v��������
		groupingBy(simpleArray);
		
		// �v�f���̒l���O���[�v�������A�w�肵�������ŏW�񂷂�
		groupingByRule(randList);
		
		// �v�f���̒l���O���[�v�������A�w�肵�������ŏW�񂵁A�w�肵���^�Œl��ԋp����
		groupingByRuleAndDefiningReturnType(randList);

		// �v�f���̒l���O���[�v�������AConcurrentHashMap�ɂĕԋp�����
		groupingByConcurrent(randList);
	}
}
