package threading;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In the classic problem FizzBuzz you are told to print the numbers from 1 to n. However when the number
 * is divisible by 3 print "Fizz". When it is divisible by 5, print "Buzz". when it is divisible by 3 and 5
 * print "FizzBuzz". In this problem, you are asked to do this in a multithreaded way. Implement a multithreaded
 * version of FizzBuzz with four threads. One thread checks for divisibility of 3 and prints "Fizz", another thread
 * is responsable with divisibility of 5 and printz "Buzz" a third thread is responsable for divisibility of 3 and 5
 * and prints "FizzBuzz". A forth thread does the numbers
 *
 * For a more elegant approach we'll use a method that returns a list of the numbers
 * @Todo finish this
 */
public class FizzBuzz {
    private List<String> result;
    private LinkedBlockingDeque<Integer> tasks;

    public FizzBuzz() {
        this.result = Collections.synchronizedList(new ArrayList<String>());
        this.tasks = new LinkedBlockingDeque<>();
    }

    private class Fizz implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    var current = tasks.pollFirst();
                    if (current % 3 == 0 && current % 5 != 0) {
                        result.add("Fizz");
                        System.out.println("Fizz " + current);
                    }  else {
                        tasks.offerFirst(current);
                    }
                }
            }
        }
    }

    private class Buzz implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    var current = tasks.pollFirst();
                    if (current % 5 == 0 && current % 3 != 0) {
                        result.add("Buzz");
                        System.out.println("Buzz " + current);
                    } else {
                        tasks.offerFirst(current);
                    }
                }
            }
        }
    }

    private class FB implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    var current = tasks.pollFirst();
                    if (current % 3 == 0 && current % 5 == 0) {
                        result.add("FizzBuzz");
                        System.out.println("FizzBuzz " + current);
                    } else {
                        tasks.offerFirst(current);
                    }
                }
            }
        }
    }

    private class Number implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    var current = tasks.pollFirst();
                    if (current % 3 != 0 && current % 5 != 0) {
                        result.add(Integer.toString(current));
                        System.out.println("Number " + current);
                    } else {
                        tasks.offerFirst(current);
                    }
                }
            }
        }
    }

    public List<String> getNumbers(int n) throws InterruptedException {
        for (var i=n; i>0; i--) {
            this.tasks.offerFirst(i);
        }
        var fizz = new Thread(new Fizz());
        fizz.start();
        var buzz = new Thread(new Buzz());
        buzz.start();
        var fizzBuzz = new Thread(new FB());
        fizzBuzz.start();
        var number = new Thread(new Number());
        number.start();

        return result;
    }
}