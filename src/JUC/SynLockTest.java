package JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2017/5/24.
 */
public class SynLockTest {
    public static void main(String[] args) {
        long value = 0;
        int MAX = 10000000;
        Lock lock = new ReentrantLock();
        long start = System.nanoTime();
        for (int i = 0; i < MAX; i++) {
            synchronized (new Object()) {
                value = value + 1;
            }
        }
        long end = System.nanoTime();
        System.out.println("synchronized cost: "+(end-start));
        start = System.nanoTime();
        for (int i = 0; i < MAX; i++) {
            lock.lock();
            try {
                value = value + 1;
            } finally {
                lock.unlock();
            }
        }
        end = System.nanoTime();
        System.out.println("lock cost: "+(end-start));
    }
}
