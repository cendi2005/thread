package threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class FutureDemo {
    public static void main(String[] args) {
                ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
                // 预创建线程
                executorService.prestartCoreThread();

                 Future future = executorService.submit(new Callable<Object>() {
             @Override
             public Object call() {
                                 System.out.println("start to run callable");
                                 Long start = System.currentTimeMillis();
                                 while (true) {
                                         Long current = System.currentTimeMillis();
                                         if ((current - start) > 1000) {
                                                 System.out.println("当前任务执行已经超过1s");
                                                 return 1;
                                             }
                                     }
                             }
         });

                 System.out.println(future.cancel(false));

                 try {
                         Thread.currentThread().sleep(3000);
                         executorService.shutdown();
                     } catch (Exception e) {
                         //NO OP
                     }
             }
}
