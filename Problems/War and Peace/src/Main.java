import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> words = new TreeMap<>();
        String[] arrayOfWords = scanner.nextLine().toLowerCase().split(" ");

        for(int i = 0; i < arrayOfWords.length; i++) {
            if(words.containsKey(arrayOfWords[i])){
                words.put(arrayOfWords[i], words.get(arrayOfWords[i]) + 1);
            } else {
                words.put(arrayOfWords[i], 1);
            }
        }
        words.forEach((key, value) -> System.out.println(key + " " + value));
    }
}