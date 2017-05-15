package package1;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by admin on 2017/5/15.
 */
public class LockSupportDemo1 {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (u) {
                System.out.println("in" + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t2.start();
        Thread.sleep(100);
        t1.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}