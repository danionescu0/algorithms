package threading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    @Test
    void getNumbers() throws InterruptedException {
        var sut = new FizzBuzz();
        var result = sut.getNumbers(10);
        Assertions.assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"), result);
    }
}