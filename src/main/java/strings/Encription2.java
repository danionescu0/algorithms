package strings;

import java.util.Arrays;
import java.util.List;

/**
 * Given an encripted  input text containing words apply the following operations
 * rearange the words in reverse order
 * for each word transform digits as for each digit replace the digit with the following letter concatenated for a number of digit times
 *
 * Example: "world he2lo"
 * transform1: he2lo world
 * transform2: hello world
 */
public class Encription2 {
    public static String decryptMessage(String encryptedMessage) {
        List<String> words = Arrays.asList(encryptedMessage.split(" "));
        StringBuilder builder = new StringBuilder();
        for (int i=words.size() -1;i >= 0; i--) {
            String word = words.get(i);
            for (int j=0; j < word.length(); j++) {
                if (Character.isDigit(word.charAt(j))) {
                    int digit = Integer.parseInt(String.valueOf(word.charAt(j)));
                    builder.append(String.valueOf(word.charAt(j + 1)).repeat(Math.max(0, digit - 1)));
                } else {
                    builder.append(word.charAt(j));
                }
            }
            builder.append(" ");
        }
        String result = builder.toString();

        return result.substring(0, result.length() - 1);
    }
}