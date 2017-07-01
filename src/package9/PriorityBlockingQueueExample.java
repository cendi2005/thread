package package9;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by admin on 2017/6/28.
 */
public class PriorityBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<PriorityElement> queue = new PriorityBlockingQueue<>();
        for (int i = 0; i < 5000; i++) {
            Random random=new Random();
            PriorityElement ele = new PriorityElement(random.nextInt(10000000));
            queue.put(ele);
        }
        while(!queue.isEmpty()){
            System.out.println(queue.take());
        }
    }
}