package threading;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.*;

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
    private LinkedBlockingQueue<Character> result;
    private CyclicBarrier cyclicBarrier;
    private Semaphore hydrogen = new Semaphore(2);
    private Semaphore oxygen = new Semaphore(1);

    @AllArgsConstructor
    class Atom implements Runnable {
        private Character element;

        @Override
        public void run() {
            try {
                if (element.toString().equals("H")) {
                    hydrogen.acquire();
                    result.offer(element);
                    cyclicBarrier.await();
                    hydrogen.release();
                } else {
                    oxygen.acquire();
                    result.offer(element);
                    cyclicBarrier.await();
                    oxygen.release();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Character> getAtoms(String atoms) throws InterruptedException {
        checkValidity(atoms);
        cyclicBarrier = new CyclicBarrier(3);
        result = new LinkedBlockingQueue<>();
        var threads = new LinkedList<Thread>();
        for (Character atom: atoms.toCharArray()) {
            var atomThread = new Thread(new Atom(atom));
            atomThread.start();
            threads.add(atomThread);
        }
        for (Thread thread: threads) {
            thread.join();
        }

        return new ArrayList<>(result);
    }

    private void checkValidity(String atoms) {
        var nrH = 0;
        var nrO = 0;
        for (Character atom: atoms.toCharArray()) {
            nrH += atom.toString().equals("H") ? 1 : 0;
            nrO += atom.toString().equals("O") ? 1 : 0;
        }
        if (nrH == 0 || nrO == 0 || nrH / nrO != 2) {
            throw new IllegalArgumentException("Not all atoms can form water");
        }
    }
}