package join.WaitAndNotify;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * http://www.cnblogs.com/xrq730/p/4855857.html
 * */
public class QueueWaitAndNotifyDemo {
    public static void main(String[] args) {
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
                            String s = ""+new Random().nextInt(1000);
                            System.out.println("我生产了一个" + s);
                            bq.put(s);
                            Thread.sleep(1000);
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
                            System.out.println("我消费了一个" + bq.take());
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread producerThread = new Thread(producerRunnable);
            Thread producerThread1 = new Thread(producerRunnable);
            Thread customerThread = new Thread(customerRunnable);
            Thread customerThread1 = new Thread(customerRunnable);
            producerThread.start();
            producerThread1.start();
            customerThread.start();
            customerThread1.start();
        }
    }
}
