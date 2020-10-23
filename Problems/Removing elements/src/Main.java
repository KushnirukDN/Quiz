import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        String[] integerString = str.split(" ");
        Set<Integer> setOfInts = new TreeSet<>();
        for (int i = 0; i < integerString.length; i++){
            setOfInts.add(Integer.parseInt(integerString[i]));
        }
        return setOfInts;
    }
    public static void removeAllNumbersGreaterThan10(Set<Integer> setOfInts) {
        setOfInts.removeIf(num -> num > 10);
    }

    /* Вариант решения со стримом:
    public static Set<Integer> getSetFromString(String str) {
        Set<Integer> set = new TreeSet<>();
        set.addAll(Arrays.stream(str.split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet()));
        return set;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        SortedSet<Integer> sort = new TreeSet<>();
        sort.addAll(set);
        set.clear();
        set.addAll(sort.headSet(11));
    }
     */

    /* Вариант решения со стримом и removeIf
      public static Set<Integer> getSetFromString(String str) {
        // write your code here
        String[] input = str.split("\\s+");
        return Arrays.stream(input)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here
        set.removeIf(n -> n > 10);
    }
     */

    /* Третий вариант, красиво засплитили изначальный массив
    Set<Integer> set = new LinkedHashSet<>();
        for (String s : str.split("\\s+")) {
            set.add(Integer.parseInt(s));
        }
        return set;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > 10) {
                iterator.remove();
            }
        }
    }
     */



}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}