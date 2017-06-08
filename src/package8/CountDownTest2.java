package package8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/5.
 */
public class CountDownTest2 {
    private static class ProcessJob implements Runnable {
        @Override
        public void run() {
            System.out.println("Some Job"+System.currentTimeMillis());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int jobSize = 20;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch stopLatch = new CountDownLatch(jobSize);
        for (int i = 0; i < jobSize; i++) {
            executorService.submit(() -> {
                try {
                    // 线程等待开始的Latch
                    startLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    new ProcessJob().run();
                } finally {
                    // 调用结束的Latch的countDown
                    stopLatch.countDown();
                }
            });
        }
        long start = System.currentTimeMillis();
        startLatch.countDown();
        // 等待所有线程执行完任务
        stopLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("Total cost time: " + (end - start));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
