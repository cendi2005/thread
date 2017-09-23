package readandlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLock2 {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(Thread thread) {
        lock.readLock().lock();
        try{
            System.out.println("start time:"+System.currentTimeMillis());
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
            System.out.println("end time:"+System.currentTimeMillis());
        }finally{
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReadAndWriteLock lock = new ReadAndWriteLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.get(Thread.currentThread());
            }
        }).start();
    }
}
