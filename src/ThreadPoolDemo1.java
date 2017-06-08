import JUC.HttpUtils2;
import com.sun.deploy.net.HttpUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2017/5/7.
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService es = Executors.newFixedThreadPool(20);
        long t1 = System.currentTimeMillis();
        for(int i=0;i<20;i++){
            es.submit(task);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("time is :"+(t2-t1));

    }




    public static class MyTask implements Runnable{

        @Override
        public void run() {
               HttpUtils2.saveImageToDisk();
        }
    }
}
