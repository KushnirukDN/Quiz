import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class CipherUtils {
    public static Map<String, String> MakeEncodeKey (String[] originalAlphabet, String[] resultingAlphabet) {
        Map<String, String> encryptionKey = new TreeMap<>();
        for (int i = 0; i < originalAlphabet.length; i++) {
            encryptionKey.put(originalAlphabet[i], resultingAlphabet[i]);
        }
        return encryptionKey;
    }

    public static Map<String, String> MakeDecodeKey (String[] originalAlphabet, String[] resultingAlphabet) {
        Map<String, String> decryptionKey = new TreeMap<>();
        for (int i = 0; i < resultingAlphabet.length; i++) {
            decryptionKey.put(resultingAlphabet[i], originalAlphabet[i]);
        }
        return decryptionKey;
    }

    public static String[] EncodeDecode (Map<String, String> map, String[] lineToEncode) {
        String[] resultingArray = new String[lineToEncode.length];

        for (int i = 0; i < lineToEncode.length; i++) {
            resultingArray[i] = map.get(lineToEncode[i]);
        }
        return resultingArray;
    }

    public static void Print (String resulting[]) {
        for (var elem : resulting)
        {
            System.out.print(elem);
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] originalAlphabet = scanner.nextLine().split("");
        String[] resultingAlphabet = scanner.nextLine().split("");
        String[] lineToEncode = scanner.nextLine().split("");
        String[] lineToDecode = scanner.nextLine().split("");
        CipherUtils.Print(CipherUtils.EncodeDecode(CipherUtils.MakeEncodeKey(originalAlphabet, resultingAlphabet), lineToEncode));
        CipherUtils.Print(CipherUtils.EncodeDecode(CipherUtils.MakeDecodeKey(originalAlphabet, resultingAlphabet), lineToDecode));
    }
}