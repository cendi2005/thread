package package8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2017/6/12.
 */

public class ConditionTest3 {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread()+ "等待条件完成");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread()+ "终于等到条件完成了，gogogo");
                    lock.unlock();
                }
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread()+ "等待条件完成");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread()+ "终于等到条件完成了，gogogo");
                    lock.unlock();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread()+ "等待条件完成");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread()+ "终于等到条件完成了，gogogo");
                    lock.unlock();
                }
            }
        }).start();




        Thread b = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    lock.lock();
                    condition.signalAll();
                    System.out.println(Thread.currentThread()+ "条件完成了，释放吧");
                } finally {
                    lock.unlock();
                }
            }
        });



        b.start();
    }
}