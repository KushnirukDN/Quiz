import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arrayOfWords = scanner.nextLine().split(" ");
        String[] arrayForNote = scanner.nextLine().split(" ");
        Map<String, Integer> newspaperWords = new TreeMap<>();
        Map<String, Integer> note = new TreeMap<>();
        boolean result = true;

        for (int i = 0; i < arrayOfWords.length; i++) {
            if (newspaperWords.containsKey(arrayOfWords[i])) {
                newspaperWords.put(arrayOfWords[i], newspaperWords.get(arrayOfWords[i]) + 1);
            } else {
                newspaperWords.put(arrayOfWords[i], 1);
            }
        }

        for (int i = 0; i < arrayForNote.length; i++) {
            if (note.containsKey(arrayForNote[i])) {
                note.put(arrayForNote[i], note.get(arrayForNote[i]) + 1);
            } else {
                note.put(arrayForNote[i], 1);
            }
        }

        for (var entry : note.entrySet()) {
            if (!newspaperWords.containsKey(entry.getKey()) ||
                    newspaperWords.get(entry.getKey()) < entry.getValue()) {
                result = false;
            }
        }

        if (!result) {
            System.out.println("You are busted");
        } else {
            System.out.println("You get money");
        }
    }
}
/*
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] a = scanner.nextLine().split("\\s+");
        String[] b = scanner.nextLine().split("\\s+");
        boolean f = true;
        List<String> list = new ArrayList<>(Arrays.asList(a));
        for (String c : b) {
            f = f && list.contains(c);
            if (!f) {
                break;
            } else {
                list.remove(c);
            }
        }
        System.out.println(f ? "You get money" : "You are busted");
    }
}
 */