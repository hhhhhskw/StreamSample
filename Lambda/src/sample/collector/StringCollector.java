package sample.collector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCollector {

	private static void joining(List<String> simpleList) {
		String s = simpleList.stream().collect(Collectors.joining());
		System.out.println(s);
	}
	
	private static void joiningWithDelimiter(List<String> intList) {
		String s = intList.stream().collect(Collectors.joining("-"));
		System.out.println(s);
	}
	
	private static void joiningWithDelimiterAndEncloser(List<String> urlList) {
		String url = urlList.stream().collect(Collectors.joining(".", "http://", "/"));
		System.out.println(url);
	}
	
	public static void main(String[] args) {
		List<String> simpleList = Arrays.asList("a", "bb", "ccc");
		List<String> telNoList = Arrays.asList("090","0000","9999");
		List<String> urlList = Arrays.asList("www", "google", "co", "jp");

		// 結合してStringに変換する
		joining(simpleList);
		
		// 結合してStringに変換する（区切り文字あり）
		joiningWithDelimiter(telNoList);
		
		// 結合してStringに変換する（区切り文字・始終端文字あり）
		joiningWithDelimiterAndEncloser(urlList);
	}
}
