package join.WaitAndNotify;

public class Customer {
    private Object lock;

    public Customer(Object lock)
    {
        this.lock = lock;
    }

    public void getValue()
    {
        try
        {
            synchronized (lock)
            {
                Thread.sleep(1000);
                //如果灭有产品，就等待
                if (ValueObject.value.equals(""))
                    lock.wait();
                System.out.println(Thread.currentThread().getName()+"Get的值是：" + ValueObject.value);
                ValueObject.value = "";
//                lock.notify();
                  lock.notifyAll();

            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
