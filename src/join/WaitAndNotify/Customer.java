package join.WaitAndNotify;

import java.util.concurrent.TimeUnit;

public class Customer implements Runnable{
    //对象监视器
    private Object monitor;

    public Customer(Object monitor)
    {
        this.monitor = monitor;
    }

//    synchronized (obj) {
//     *         while (&lt;condition does not hold&gt;)
//     *             obj.wait();
//     *         ... // Perform action appropriate to condition
//     *     }
//

    public void getValue()
    {
        try
        {
            synchronized (monitor)
            {
//                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
                //如果灭有产品，就等待
                if (ValueObject.value.equals(""))
                    monitor.wait();
                System.out.println(Thread.currentThread().getName()+"Get的值是：" + ValueObject.value);
                ValueObject.value = "";
//                lock.notify();
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
       while (true){
//           this.getValue();

           synchronized (monitor)
           {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               //如果灭有产品，就等待
               if (ValueObject.value.equals(""))
                   try {
                       monitor.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               System.out.println(Thread.currentThread().getName()+"Get的值是：" + ValueObject.value);
               ValueObject.value = "";
//                lock.notify();
               monitor.notifyAll();

           }



       }
    }
}
