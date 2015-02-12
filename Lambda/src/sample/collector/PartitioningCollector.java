package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartitioningCollector {

	private static void partitioningBy(List<String> randList) {
		Map<Boolean, List<String>> map = randList.stream().collect(Collectors.partitioningBy(i -> i.length() == 3));
		System.out.println(map);
	}
	
	private static void partitioningByRule(List<String> randList) {
    	Pattern p = Pattern.compile("^[0-9]*$");
    	Stream<String> stream = randList.stream();
		Map<Boolean, String> map = stream.collect(Collectors.partitioningBy(a -> p.matcher(a).find(), Collectors.mapping(s -> s.toString(), Collectors.joining())));
		System.out.println(map);
	}
	
	public static void main(String[] args) {
		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");

		// �����𖞂����O���[�v�Ɩ������Ȃ��O���[�v�ɕ�����
		partitioningBy(randList);
		
		// �����𖞂����O���[�v�Ɩ������Ȃ��O���[�v�ɕ����A�w�肵�������ŏW�񂷂�
		partitioningByRule(randList);
	}
}
