package package9;

import java.util.concurrent.DelayQueue;

/**
 * Created by admin on 2017/6/28.
 */
public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> queue= new DelayQueue<>();
        DelayedElement ele= new DelayedElement( "cache 3 seconds",2000);
        queue.put( ele);
        System. out.println( queue.take());

    }
}
