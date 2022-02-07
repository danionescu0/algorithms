package threading;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

/**
 * There are two kinds of threads: oxygen and hydrogen. Your goal is to group these threads to form water molecules.
 *
 * There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads
 * will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier.
 * These threads should pass the barrier in groups of three, and they must immediately bond with each other
 * to form a water molecule. You must guarantee that all the threads from one molecule bond before
 * any other threads from the next molecule do.
 *
 * In other words:
 *
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present, it must wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present, it must wait for an oxygen thread
 * and another hydrogen thread.
 * We do not have to worry about matching the threads up explicitly; the threads do not necessarily know which
 * other threads they are paired up with. The key is that threads pass the barriers in complete sets;
 * thus, if we examine the sequence of threads that bind and divide them into groups of three,
 * each group should contain one oxygen and two hydrogen threads.
 *
 * Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
 */
public class BuildingH2O {
    private ConcurrentLinkedDeque<Character> result;
    private ConcurrentLinkedDeque<Character> elementsQueue;
    private CyclicBarrier cyclicBarrier;

    @AllArgsConstructor
    class Atom implements Runnable {
        private Character element;

        @Override
        public void run() {
            var nrUntilCompletion = result.size() % 3;
            var descendingIte = result.descendingIterator();
            var i = 0;
            var builder = new StringBuilder();
            while (descendingIte.hasNext() && i < nrUntilCompletion) {
                builder.append(descendingIte.next());
                i++;
            }
            var lastTwo = builder.toString();
            if (lastTwo.equals("")
                ||lastTwo.equals("O") && element.toString().equals("H")
                || lastTwo.equals("OH") && element.toString().equals("H")
                || lastTwo.equals("HH") && element.toString().equals("O")
                || lastTwo.equals("H") && element.toString().equals("O")) {
                result.offerFirst(element);
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting for others to reach barrier.");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    System.out.println("intrerupted");
                } catch (BrokenBarrierException e) {
                    System.out.println("brokern barier");
                }
            } else {
                elementsQueue.offerLast(element);
            }

        }
    }

    class Finis implements Runnable {
        @Override
        public void run() {
            System.out.println("Printing iteration:");
            for (Character character: result) {
                System.out.print(character);
            }
        }
    }

    public List<Character> getAtoms(String atoms) {
        result = new ConcurrentLinkedDeque<>();
        var individualAtoms = atoms.chars()
                .mapToObj(value -> (char) value)
                .collect(Collectors.toList());
        elementsQueue = new ConcurrentLinkedDeque<>(individualAtoms);
        cyclicBarrier = new CyclicBarrier(3, new Finis());
        var i = 0;
        while (!elementsQueue.isEmpty()) {
            var queueElement = elementsQueue.pollFirst();
            var atom = new Thread(new Atom(queueElement));
            atom.setName(String.format("T{%s} ({%s})", i, queueElement));
            atom.start();
            i++;
        }


        return new ArrayList<>();
    }
}