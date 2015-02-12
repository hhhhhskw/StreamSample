package sample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeTypeStream {

	private static void toArray(List<String> randList) {
		String[] array = randList.stream().toArray(String[]::new);
		System.out.println(Arrays.toString(array));
	}

	private static void build(List<String> randList) {
		Stream.Builder<String> builder = Stream.builder();
	     for (int i=1; i<=100; i++) {
	            if (i % 15 == 0) {
	            	builder.accept("FizzBuzz");
	            } else if (i % 3 == 0) {
	            	builder.accept("Fizz");
	            } else if (i % 5 == 0) {
	            	builder.accept("Buzz");
	            } else {
	            	builder.accept(String.valueOf(i));
	            }
	        }
		System.out.println(builder.build().collect(Collectors.toList()));
	}

	public static void main(String[] args) {

		List<String> randList = Arrays.asList("1", "ccc", "bb", "333", "22", "a");
	
		// ƒŠƒXƒg(String)‚ð”z—ñ(String)‚É•ÏŠ·‚·‚é
		toArray(randList);
		
		// FizzBuzz
		build(randList);
	}
}
