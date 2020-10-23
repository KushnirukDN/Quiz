import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = new LinkedHashSet<>();
        Set<String> text = new LinkedHashSet<>();

        int d = scanner.nextInt();
        for(int i = 0; i < d ; i++) {
            dictionary.add(scanner.next().toLowerCase());
        }
        int l = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < l ; i++) {
            String str = scanner.nextLine().toLowerCase();
            text.addAll(Arrays.asList(str.split("\\s+")));
        }
        text.removeAll(dictionary);
        text.forEach(System.out::println);
    }
}





