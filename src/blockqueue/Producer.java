package blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by admin on 2017/7/3.
 */
public class Producer implements Runnable{

    //容器
    private final ArrayBlockingQueue<Bread> queue;

    public Producer(ArrayBlockingQueue<Bread> queue){
        this.queue = queue;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while(true){
            produce();
        }
    }

    public void produce(){
        /**
         * put()方法是如果容器满了的话就会把当前线程挂起
         * offer()方法是容器如果满的话就会返回false。
         */
        try {
            Bread bread = new Bread();
            queue.put(bread);
            System.out.println("Producer:"+bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}