package package8;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by admin on 2017/6/8.
 */
public class CyclicBarrierTest {

    private static int SIZE = 3;

    private static Map result = new HashMap();

    private static CyclicBarrier cb;

    public static void main(String[] args) {
        System.out.println("工作线程开始工作。。。。。。。。");
        cb = new CyclicBarrier(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new MyTask().start();
        }



    }

    static class MyTask extends Thread {
        @Override
        public void run() {
            try {

                System.out.println("线程" + Thread.currentThread().getName() + "     正在执行清算任务...");
                // 模拟业务逻辑的耗时
                Thread.sleep(new Random().nextInt(500));
                // 用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
                System.out.println("线程" + Thread.currentThread().getName() + "     自己的任务完成，等待其他线程完成,run方法还没全部执行完");
                cb.await();
                System.out.println("线程" + Thread.currentThread().getName() + "     run方法执行完成");



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

}