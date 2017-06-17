import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 2017/5/3.
 */
public class CountDownLatchDemo implements Runnable{
    static final CountDownLatch end = new CountDownLatch(5);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();


    @Override
    public void run() {
        //检查任务
        try {
            System.out.println(Thread.currentThread().getName()+"is run.........");
            end.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newFixedThreadPool(5);

        long t1 = System.currentTimeMillis();
        for(int i=0;i<5;i++){
            exec.submit(demo);
        }
        //等待检查
        end.await();
        //发射火箭
        System.out.println("fire!");
        //全部执行完了，结束
        exec.shutdown();
        long t2 = System.currentTimeMillis();
        System.out.println("all fire complte! use "+(t2-t1));

    }







}
