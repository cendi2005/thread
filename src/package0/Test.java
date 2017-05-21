package package0;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2017/5/20.
 */
public class Test {
    public static void main(String[] args) {

//         ReentrantLock lock = new ReentrantLock();
//  lock.lock();
        System.out.println(Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());

        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());




    }








}
