import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 2017/5/6.
 */
public class BlockingQueueDemo {
    public static void main(String[] args)
    {

        //·ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
        final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(5);
        Runnable producerRunnable = new Runnable()
        {
            int i = 0;
            public void run()
            {
                while (true)
                {
                    try
                    {
                        System.out.println("product a thing" + i++);
                        bq.put(i + "");
                        Thread.sleep(500);//生产快
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable customerRunnable = new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        System.out.println("get a thing" + bq.take());
                        Thread.sleep(1000);//消费慢
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread producerThread = new Thread(producerRunnable);
        Thread customerThread = new Thread(customerRunnable);
        producerThread.start();
        customerThread.start();
        Thread producerThread1 = new Thread(producerRunnable);
        Thread customerThread1 = new Thread(customerRunnable);
        producerThread1.start();
        customerThread1.start();
    }
}
