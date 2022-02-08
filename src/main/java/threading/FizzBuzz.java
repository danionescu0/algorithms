package threading;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * In the classic problem FizzBuzz you are told to print the numbers from 1 to n. However when the number
 * is divisible by 3 print "Fizz". When it is divisible by 5, print "Buzz". when it is divisible by 3 and 5
 * print "FizzBuzz". In this problem, you are asked to do this in a multithreaded way. Implement a multithreaded
 * version of FizzBuzz with four threads. One thread checks for divisibility of 3 and prints "Fizz", another thread
 * is responsable with divisibility of 5 and printz "Buzz" a third thread is responsable for divisibility of 3 and 5
 * and prints "FizzBuzz". A forth thread does the numbers
 *
 * For a more elegant approach we'll use a method that returns a list of the numbers
 */
public class FizzBuzz {
    private List<String> result;
    private AtomicInteger current;
    private Integer total;
    private final Semaphore lock = new Semaphore(1);

    private class Fizz implements Runnable {
        private Predicate<Integer> condition;
        private Function<Integer, String> print;

        public Fizz(Predicate<Integer> condition, Function<Integer, String> print) {
            this.condition = condition;
            this.print = print;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                var nr = current.get();
                if (nr >= total) {
                    Thread.currentThread().interrupt();
                    break;
                }
                if (condition.test(nr)) {
                    result.set(nr - 1, print.apply(nr));
                    current.incrementAndGet();
                }
                lock.release();
            }
        }
    }

    public List<String> getNumbers(int n) throws InterruptedException {
        this.result = Collections.synchronizedList(new ArrayList<>(n));
        for (var i=1; i <= n; i++) {
            this.result.add("0");
        }
        current = new AtomicInteger(1);
        total = n + 1;

        var fizz = new Thread(new Fizz(nr -> nr % 3 == 0 && nr % 5 != 0, nr -> "Fizz"));
        fizz.start();
        var buzz = new Thread(new Fizz(nr -> nr % 5 == 0 && nr % 3 != 0, nr -> "Buzz"));
        buzz.start();
        var fizzBuzz = new Thread(new Fizz(nr -> nr % 5 == 0 && nr % 3 == 0, nr -> "FizzBuzz"));
        fizzBuzz.start();
        var number = new Thread(new Fizz(nr -> nr % 5 != 0 && nr % 3 != 0, nr -> Integer.toString(nr)));
        number.start();
        while (fizz.isAlive() && buzz.isAlive() && fizzBuzz.isAlive() && number.isAlive()) {
            Thread.sleep(10);
        }

        return result;
    }
}