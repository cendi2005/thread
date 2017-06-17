
import org.omg.PortableServer.THREAD_POLICY_ID;
import package4.ThreadPool;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2017/5/6.
 */
public class Test {
    public static void main(String[] args) {



       Thread t = Thread.currentThread();


        System.out.println("start...");
        LockSupport.park(t);
        System.out.println("parking....");
        LockSupport.unpark(t);
        System.out.println("unparking...");


    }





}
