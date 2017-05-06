import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 2017/5/6.
 */
public class BlockingQueueDemo {
    public static void main(String[] args)
    {
        final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(10);
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
                        Thread.sleep(1000);//生产快
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
                        Thread.sleep(3000);//消费慢
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
    }
}
