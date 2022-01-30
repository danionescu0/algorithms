package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringNoRepeatTest {
    @Test
    void lengthOfLongestSubstring() {
        var sut = new LongestSubstringNoRepeat();
        Assertions.assertEquals(7, sut.lengthOfLongestSubstring("cine are mere multe"));
        Assertions.assertEquals(3, sut.lengthOfLongestSubstring("abcabcbb"));
        Assertions.assertEquals(1, sut.lengthOfLongestSubstring("bbbbb"));
        Assertions.assertEquals(3, sut.lengthOfLongestSubstring("pwwkew"));
        Assertions.assertEquals(0, sut.lengthOfLongestSubstring(""));
        Assertions.assertEquals(2, sut.lengthOfLongestSubstring("au"));
    }
}