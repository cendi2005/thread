package package8;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by admin on 2017/6/17.
 */
public class SemaphoreTest1 {
    private static final int COUNT = 10;
    private static Executor executor = Executors.newFixedThreadPool(COUNT);
    private static Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) {
        for (int i=0; i< COUNT; i++) {
            executor.execute(new SemaphoreTest1.Task());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"   work start......");
                System.out.println();
                //读取文件操作
                semaphore.acquire();
                //模拟耗时
                Thread.sleep(2000);
                // 存数据过程
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+"   work end..........");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}