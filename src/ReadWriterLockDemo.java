import sun.awt.windows.ThemeReader;

import javax.management.relation.RelationNotFoundException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 2017/5/3.
 */
public class ReadWriterLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLokc = readWriteLock.writeLock();
    private int value;
    public Object handleRead(Lock lock) throws Exception{
        long start = System.currentTimeMillis();
        lock.lock();//模拟读操作
        Thread.sleep(1000);//读操作耗时越多，读写锁的优势越明显
        lock.unlock();
        long end = System.currentTimeMillis();
        System.out.println("use "+ (end-start));
        return value;
    }

    public void handleWrite(Lock lock,int index) throws Exception{
        lock.lock();//模拟写操作
        Thread.sleep(1000);//读操作耗时越多，读写锁的优势越明显
        value=index;
        System.out.println("write...,"+value);
        lock.unlock();
    }


    public static void main(String[] args) {
        final ReadWriterLockDemo demo = new ReadWriterLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {

                try {

                    demo.handleRead(readLock);
                    //demo.handleRead(lock);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {

                try {
                    demo.handleWrite(writeLokc,new Random().nextInt(10));
                    //demo.handleWrite(lock,new Random().nextInt(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

         for(int i=0;i<18;i++){
             new Thread(readRunnable).start();
         }

         for(int i=18;i<20;i++){
             new Thread(writeRunnable).start();
         }



    }






}
