import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableServer.THREAD_POLICY_ID;
import package4.ThreadPool;

import java.util.concurrent.locks.LockSupport;

public class Test {



    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.interrupted());

    }


}