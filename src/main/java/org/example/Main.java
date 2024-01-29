package org.example;

import org.example.models.Employer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		final var employers = List.of(
				new Employer("Леонид", 25, "программист"),
				new Employer("Василий", 25, "инженер"),
				new Employer("Василий", 22, "инженер"),
				new Employer("Алиса", 44, "инженер"),
				new Employer("Кузьма", 46, "программист"),
				new Employer("Никита", 13, "школьник"),
				new Employer("Виталий", 30, "инженер"),
				new Employer("Дмитрий", 27, "инженер")
		);

		System.out.println(deduplication(List.of(1, 3, 2, 1, 5, 2, 4)));
		System.out.println(findHighestNumber(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13), 2));
		System.out.println(findHighestUniqueNumber(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13), 2));
		System.out.println(findOldestEmployers(employers));
		System.out.println(findAverageAge(employers));
		System.out.println(findLargesString(List.of("asdasdasd", "sadasd", "dsasdad", "sdawasd", "", "sdasda")));
		System.out.println(findCountsMap("asd asd ww asd dd ww"));
		printSortedStrings(List.of("дом", "ток", "дима", "кот", "", "дуб", "дрозд"));
		System.out.println(findLargestString(new String[]{
				"ad asdw wsado ssda wqwe",
				"asdwqed esdad wewqww dds g",
				"sdadsdasdasdhjadh ww sx s q",
				"sadewqeoooo s sdaisdiw s ppp",
				"ssda wqwe ssadwq sssd d"
		}));
	}

	private static List<?> deduplication(List<?> list) {
		return list.stream().distinct().toList();
	}

	private static Integer findHighestNumber(List<Integer> list, int num) {
		return list.size() <= num
				? null
				: list.stream()
				.sorted((a, b) -> Integer.compare(b, a))
				.toList().get(num);
	}

	private static Integer findHighestUniqueNumber(List<Integer> list, int num) {
		return list.size() <= num
				? null
				: list.stream().distinct()
				.sorted((a, b) -> Integer.compare(b, a))
				.toList().get(num);
	}

	private static List<String> findOldestEmployers(List<Employer> employers) {
		return employers.stream()
				.filter(it -> "инженер".equalsIgnoreCase(it.position()))
				.sorted((e1, e2) -> Integer.compare(e2.age(), e1.age()))
				.map(Employer::name)
				.limit(3).toList();
	}

	private static double findAverageAge(List<Employer> employers) {
		return employers.stream()
				.filter(it -> "инженер".equalsIgnoreCase(it.position()))
				.mapToDouble(it -> (double) it.age()).average()
				.orElseThrow();
	}

	private static String findLargesString(List<String> strings) {
		return strings.stream()
				.max(Comparator.comparingInt(String::length))
				.orElseThrow();
	}

	private static Map<String, Integer> findCountsMap(String string) {
		return Arrays.stream(string.split(" "))
				.collect(Collectors.toMap(Function.identity(), str -> 1, Integer::sum));
	}

	private static void printSortedStrings(List<String> strings) {
		strings.stream()
				.sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
				.forEach(System.out::println);
	}

	private static String findLargestString(String[] strings) {
		return Arrays.stream(strings)
				.flatMap(it -> Arrays.stream(it.split(" ")))
				.max(Comparator.comparingInt(String::length))
				.orElseThrow();
	}
}