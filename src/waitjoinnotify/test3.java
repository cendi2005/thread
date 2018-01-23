package waitjoinnotify;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列解法
 * 生产者和消费者问题
 *
 * @author bridge
 */
public class test3 {

    public static void main(String[] args) {
        Resource resource = new Resource();
        //生产者线程
        ProducerThread p = new ProducerThread(resource);
        ProducerThread p1 = new ProducerThread(resource);
        //多个消费者
        ConsumerThread c1 = new ConsumerThread(resource);
        ConsumerThread c2 = new ConsumerThread(resource);
        ConsumerThread c3 = new ConsumerThread(resource);

        p.start();
        p1.start();
        c1.start();
         c2.start();
         c3.start();
    }

}


class ProducerThread extends Thread {

    private Resource resource;

    public ProducerThread(Resource resource) {
        this.resource = resource;
        //setName("生产者");
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}

class ConsumerThread extends Thread {
    private Resource resource;

    public ConsumerThread(Resource resource) {
        this.resource = resource;
        //setName("消费者");
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}


/**
 * 公共资源类
 */
class Resource {


    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);

    /***
     * 向资源池中添加资源
     */
    public  void add() {

        try {
            System.out.println(Thread.currentThread().getName()+" add");

            // add 虽然是同步
            resourceQueue.put(1);
            //但是当这里打印的时候，可能其他线程已经取出来了，所以造成add后还是显示0个资源
            System.out.println(System.nanoTime()+"_生产者" + Thread.currentThread().getName() + "生产一件资源," + "当前资源池有" + resourceQueue.size() + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从资源池中取走资源
     */
    public  void remove() {
        try {
            int x = (int)resourceQueue.take();
            System.out.println(System.nanoTime()+"_消费者" + Thread.currentThread().getName() + "消耗一件资源," + "当前资源池有" + resourceQueue.size() + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}