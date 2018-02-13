package join;

import java.util.concurrent.TimeUnit;

public class JoinTest2 {
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 3; i++) {
// 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
    static class Domino implements Runnable {
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }
        public void run() {
            try {
                System.out.println("Thread current :"+ Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
