package threadpool;

/**
 * Created by admin on 2017/6/26.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}
