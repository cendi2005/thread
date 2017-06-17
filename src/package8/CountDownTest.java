package package8;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by admin on 2017/6/4.
 */
public class CountDownTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(4);

        System.out.println("in " + Thread.currentThread().getName() + "...");
        System.out.println("before latch.await()...");
        for (int i = 1; i <= 3; i++) {
            new Thread("T" + i) {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    latch.countDown();
                }
            }.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in " + Thread.currentThread().getName() + "...");
        System.out.println("after latch.await()...");

    }
}
