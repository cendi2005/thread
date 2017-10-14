package join.WaitAndNotify;

public class Producer {
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
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
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
}
