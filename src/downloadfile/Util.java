package downloadfile;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Util {


    public static void main(String[] args) throws Exception{


        RandomAccessFile read = new RandomAccessFile("/Users/admin/Desktop/thread/src/downloadfile/1.png","rw");

        int filesize = (int)read.length();

        System.out.println("read file size:"+read.length());



        int threadCount = 3;
        int readSize = 0;
        if(filesize%threadCount==0){
            readSize = filesize/threadCount;
        }else{
            readSize = filesize/threadCount+1;
        }

        System.out.println("readSize:"+readSize);

//
//        for(int i=0;i<threadCount;i++){
//            FileThread fileThread = new FileThread("/Users/admin/Desktop/thread/src/downloadfile/1.png",i*readSize,readSize);
//            fileThread.start();
//        }

        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();

        // 创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < 2; i++) {
            // 传入Callable对象创建FutureTask对象
            FutureTask<Integer> ft = new FutureTask<Integer>(new FileThread1("/Users/admin/Desktop/thread/src/downloadfile/1.png",i*readSize,readSize));
            taskList.add(ft);
            // 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
            exec.submit(ft);
        }

        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("多任务计算后的总结果是:" + totalResult);


    }




}
