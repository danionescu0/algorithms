package strings;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Encription2Test {
    @Test
    public void test1() {
        Assertions.assertEquals("hello world", Encription2.decryptMessage("world he2lo"));

    }
}
