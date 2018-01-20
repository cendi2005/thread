package waitjoinnotify;

import java.util.concurrent.TimeUnit;

public class WaitNotifyCase {
    public static void main(String[] args) {
        //Threadx lock = new ThreadX();
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println(" A get lock");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(" A do wait method");
                        lock.wait();//这个挂起的是A线程
                        System.out.println(" A wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" B is waiting to get lock");
                synchronized (lock) {
                    System.out.println(" B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    System.out.println(" B do notify method");
                }
            }
        }).start();
    }
}

