package join.WaitAndNotify;

import java.util.Random;

public class Producer implements Runnable{
    private Object monitor;

    public Producer(Object monitor)
    {
        this.monitor = monitor;
    }

    public void setValue()
    {
        try
        {
            synchronized (monitor)
            {
                Thread.sleep(1000);
                //如果有产品了，就不生产
                if (!ValueObject.value.equals(""))
                    monitor.wait();
                String value = ""+new Random().nextInt(1000);
                System.out.println(Thread.currentThread().getName()+ "Set的值是：" + value);
                ValueObject.value = value;
//               monitor.notify();
                monitor.notifyAll();
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
//            this.setValue();
            synchronized (monitor)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //如果有产品了，就不生产
                if (!ValueObject.value.equals(""))
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                String value = ""+new Random().nextInt(1000);
                System.out.println(Thread.currentThread().getName()+ "Set的值是：" + value);
                ValueObject.value = value;
//               monitor.notify();
                monitor.notifyAll();
            }


        }
    }
}
