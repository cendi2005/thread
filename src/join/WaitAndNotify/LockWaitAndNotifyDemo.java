package join.WaitAndNotify;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockWaitAndNotifyDemo extends ReentrantLock{
    private Condition condition = newCondition();

    public void set()
    {
        try
        {
            this.lock();
            while (!"".equals(ValueObject.value))
                condition.await();
            Thread.sleep(1000);
            ValueObject.value = ""+new Random().nextInt(1000);
            System.out.println(Thread.currentThread().getName() + "生产了value, value的当前值是" + ValueObject.value);
            condition.signal();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            unlock();
        }
    }

    public void get()
    {
        try
        {
            this.lock();
            while ("".equals(ValueObject.value))
                condition.await();
            Thread.sleep(1000);

            ValueObject.value = "";
            System.out.println(Thread.currentThread().getName() + "消费了value, value的当前值是" + ValueObject.value);
            condition.signal();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            unlock();
        }
    }
}
