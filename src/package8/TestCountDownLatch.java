package package8;

import java.util.concurrent.CountDownLatch;

/**
 * Created by admin on 2017/5/17.
 */
public class TestCountDownLatch {

    private static final int taskNum = 3;

    static class MyRunnable implements Runnable {

        private int num;

        private CountDownLatch cdl;

        public MyRunnable(int num, CountDownLatch cdl){
            this.num = num;
            this.cdl = cdl;
        }

        public void run() {
            System.out.println("第" + num + "-->"+Thread.currentThread().getName()+"个线程开始执行任务...");
            try {
                Thread.sleep(1000); // 模拟任务耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第" + num + "-->"+Thread.currentThread().getName()+"个线程任务执行结束...");
            cdl.countDown();
        }
    }

    public static void main(String[] args) throws Exception{
        CountDownLatch cdl = new CountDownLatch(taskNum);
        CountDownLatch cd2 = new CountDownLatch(3);

        for (int i = 1; i <= taskNum; i++) {
            MyRunnable mr = new MyRunnable(i, cdl);
            Thread t = new Thread(mr);
            t.start();
        }
        System.out.println("main线程完成任务才继续执行...");

        System.out.println(Thread.currentThread().getName());
        cdl.await();
        System.out.println(Thread.currentThread().getName());

        System.out.println("其他线程完成任务,main开始继续执行...");
        System.out.println("main任务完成，整个任务进度完成...");

        System.out.println("=====另外一个计数器开始计数======");
        cd2.await();
        //cd2.countDown();
        System.out.println("-1");
        //cd2.countDown();
        System.out.println("-2");
        System.out.println("=====另外一个计数器倒计数完成======");
        System.out.println("所有的都完成啦");
    }
}
