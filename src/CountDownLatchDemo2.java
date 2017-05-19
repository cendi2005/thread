import java.util.concurrent.CountDownLatch;

/**
 * Created by admin on 2017/5/6.
 */
public class CountDownLatchDemo2 {
    private static class WorkThread extends Thread
    {
        private CountDownLatch cdl;
        private int sleepSecond;

        public WorkThread(String name, CountDownLatch cdl, int sleepSecond)
        {
            super(name);
            this.cdl = cdl;
            this.sleepSecond = sleepSecond;
        }

        public void run()
        {
            try
            {
                System.out.println(this.getName() + "   start, time is "+ System.currentTimeMillis());
                Thread.sleep(sleepSecond * 1000);
                cdl.countDown();//减少一个
                System.out.println(this.getName() + "execute , time is " + System.currentTimeMillis());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static class DoneThread extends Thread
    {
        private CountDownLatch cdl;

        public DoneThread(String name, CountDownLatch cdl)
        {
            super(name);
            this.cdl = cdl;
        }

        public void run()
        {
            try
            {
                System.out.println(this.getName() + "   waiting start .. time is" + System.currentTimeMillis());
                cdl.await();
                System.out.println(this.getName() + "   wait end,time is " + System.currentTimeMillis());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        CountDownLatch cdl = new CountDownLatch(3);
        DoneThread dt0 = new DoneThread("DoneThread1", cdl);
        DoneThread dt1 = new DoneThread("DoneThread2", cdl);
        dt0.start();
        dt1.start();
        WorkThread wt0 = new WorkThread("WorkThread1", cdl, 2);
        WorkThread wt1 = new WorkThread("WorkThread2", cdl, 3);
        WorkThread wt2 = new WorkThread("WorkThread3", cdl, 4);
        wt0.start();
        wt1.start();
        wt2.start();
    }
}
