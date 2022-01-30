package strings;

import java.util.LinkedHashSet;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class LongestSubstringNoRepeat {
    public int lengthOfLongestSubstring(String text) {
        if (text.length() == 0) {
            return 0;
        }
        var chars = text.toCharArray();
        var longest = 1;
        for (int i=0; i < chars.length - 1; i++) {
            var currentBuffer = new LinkedHashSet<Character>();
            currentBuffer.add(chars[i]);
            for (int j=i + 1; j < chars.length; j++) {
                if (currentBuffer.contains(chars[j])) {
                    break;
                }
                longest = Math.max(longest, currentBuffer.size() + 1);
                currentBuffer.add(chars[j]);
            }
        }

        return longest;
    }
}