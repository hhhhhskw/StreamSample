package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GeneralCollector {

	private static void mapping(List<String> simpleList) {
		String str = simpleList.stream().collect(Collectors.mapping(a -> a.toUpperCase(), Collectors.joining("|")));
		System.out.println(str);
	}
	
	private static void reduceVal(List<String> simpleList) {
		Optional<String> qw = simpleList.stream().collect(Collectors.reducing((q, w) -> q + w));
		System.out.println(qw);
	}
	
	private static void reduceValWithDefaultVal(List<String> simpleList) {
		String str = simpleList.stream().collect(Collectors.reducing("xyz | ", (i, g) -> i + g));
		System.out.println(str);
	}
	
	private static void reduceValWithDefaultValAndDefineMappingRule(List<String> strList) {
		Integer result = strList.stream().collect(Collectors.reducing(-5, i -> i.length(), (g, a) -> g + a));
		System.out.println(result); // 1
	}
	
	public static void main(String[] args) {
		List<String> simpleList = Arrays.asList("a", "bb", "ccc");

		// �l��ϊ����đ���Collector���Ăяo��
		mapping(simpleList);

		// �l���W�񂷂� (���߂�l��Optional�^)
		reduceVal(simpleList);

		// �l���W�񂷂�(�����l�̎w�肠��)(���߂�l��String�^)
		reduceValWithDefaultVal(simpleList);

		// �l���W�񂷂�(�����l�A����ђl�̏W�񃋁[���̎w�肠��)(���߂�l�̌^�͒l�̏W�񃋁[���ɂ���ĕς��)
		reduceValWithDefaultValAndDefineMappingRule(simpleList);
	}
}
