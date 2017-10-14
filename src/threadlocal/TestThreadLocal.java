package threadlocal;

import java.util.Random;

/**
 * ThreadLocal和synchronized的区别?
 * */
public class TestThreadLocal {
    private static final ThreadLocal<String> value = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return 0 + "";
        }
    };


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    static class MyThread implements Runnable {
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("线程" + index + "的初始value:" + value.get());
            value.set("connection:" + value.get() + new Random().nextInt(100));
            System.out.println("线程" + index + "的累加value:" + value.get());
        }
    }
}
