package join.WaitAndNotify;

import java.util.Random;

public class Producer implements Runnable{
    private Object lock;

    public Producer(Object lock)
    {
        this.lock = lock;
    }

    public void setValue()
    {
        try
        {
            synchronized (lock)
            {
                Thread.sleep(1000);
                //如果有产品了，就不生产
                if (!ValueObject.value.equals(""))
                    lock.wait();
                String value = ""+new Random().nextInt(1000);
                System.out.println(Thread.currentThread().getName()+ "Set的值是：" + value);
                ValueObject.value = value;
//               lock.notify();
                lock.notifyAll();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true)
        {
            //不加this，线程就会错乱
            this.setValue();
        }
    }
}
