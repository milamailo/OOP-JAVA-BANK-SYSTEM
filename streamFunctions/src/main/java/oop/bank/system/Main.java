package oop.bank.system;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}



public class FindDuplicates {
    public static Set<Integer> findDuplicates(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 1, 2, 4, 5);
        Set<Integer> duplicates = findDuplicates(numbers);
        System.out.println("Duplicate elements: " + duplicates); // Output: Duplicate elements: [1, 2]
    }
}
