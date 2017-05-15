import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2017/5/3.
 */
public class ReenterLock implements Runnable {
     public static ReentrantLock lock = new ReentrantLock();
     public static int i = 0;


    public static void main(String[] args) throws Exception{
        ReenterLock t = new ReenterLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i:"+i);
    }




    @Override
    public void run() {
        for(int j=0;j<2;j++){

            lock.lock();

            try {
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }



        }
    }
}
