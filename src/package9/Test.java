package package9;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by admin on 2017/6/24.
 */
public class Test {
    public static void main(String[] args) throws Exception{

        //·ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        arrayBlockingQueue.add("aa");

        //·LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        linkedBlockingQueue.put("aa");

        //·PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        priorityBlockingQueue.add("aa");

        //·DelayQueue：一个使用优先级队列实现的无界阻塞队列。
        DelayQueue delayQueue = new DelayQueue();
       // delayQueue.add("aa");

        /**
         * ·SynchronousQueue：一个不存储元素的阻塞队列。
         ·LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
         ·LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列
         *
         *
         * */

    }
}
