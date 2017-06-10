package package8;

/**
 * Created by admin on 2017/6/10.
 */

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

/**
 */
class MyThread extends Thread {
    private CyclicBarrier cb;

    public MyThread(String name, CyclicBarrier cb) {
        super(name);
        this.cb = cb;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " going to await");
        try {
            cb.await();
            System.out.println(Thread.currentThread().getName() + "work done ,go continue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cb = new CyclicBarrier(3, new Thread("barrierAction") {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " barrier action");

            }
        });
        MyThread t1 = new MyThread("t1", cb);
        MyThread t2 = new MyThread("t2", cb);
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " going to await");
        cb.await();
        System.out.println(Thread.currentThread().getName() + " continue");

    }
}