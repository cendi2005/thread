package blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by admin on 2017/7/3.
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int capacity = 10;
        ArrayBlockingQueue<Bread> queue = new ArrayBlockingQueue<Bread>(capacity);

        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

}
