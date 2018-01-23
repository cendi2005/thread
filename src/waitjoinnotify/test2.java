//package waitjoinnotify;
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * lock和condition解法
// * 生产者和消费者问题
// *
// */
//public class test2 {
//
//    public static void main(String[] args) {
//        Lock lock = new ReentrantLock();
//        Condition pCondition = lock.newCondition();
//        Condition cCondition = lock.newCondition();
//
//        Resource resource = new Resource(lock, pCondition, cCondition);
//
//        //生产者线程
//        ProducerThread p = new ProducerThread(resource);
//        ProducerThread p1 = new ProducerThread(resource);
//        //多个消费者
//        ConsumerThread c1 = new ConsumerThread(resource);
//        ConsumerThread c2 = new ConsumerThread(resource);
//        ConsumerThread c3 = new ConsumerThread(resource);
//
//        p.start();
//        p1.start();
//        c1.start();
//        c2.start();
//        c3.start();
//    }
//}
//
///**
// * 生产者线程
// */
//class ProducerThread extends Thread {
//
//    private Resource resource;
//
//    public ProducerThread(Resource resource) {
//        this.resource = resource;
//        setName("生产者");
//    }
//
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep((long) (1000 * Math.random()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            resource.add();
//        }
//    }
//}
//
//
//
///**
// * 消费者线程
// */
//class ConsumerThread extends Thread {
//
//    private Resource resource;
//
//    public ConsumerThread(Resource resource) {
//        this.resource = resource;
//        //setName("消费者");
//    }
//
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep((long) (1000 * Math.random()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            resource.remove();
//        }
//    }
//}
//
//
///**
// * 公共资源类
// */
//class Resource {
//
//    //当前资源数量
//    private int num = 0;
//    //资源池中允许存放的资源数
//    private int size = 3;
//
//    private Lock lock;
//    private Condition pCondition;
//    private Condition cCondition;
//
//    public Resource(Lock lock, Condition pCondition, Condition cCondition) {
//        this.lock = lock;
//        this.pCondition = pCondition;
//        this.cCondition = cCondition;
//
//    }
//
//    /***
//     * 向资源池中添加资源
//     */
//    public void add() {
//
//        lock.lock();
//        try {
//            if (num < size) {
//                num++;
//                System.out.println(Thread.currentThread().getName() + "生产一件资源,当前资源池有" + num + "个");
//                //唤醒等待的消费者
//                cCondition.signalAll();
//            } else {
//                //使生产者等待
//                pCondition.await();
//                System.out.println(Thread.currentThread().getName() + "线程进入等待");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//
//    /**
//     * 从资源池中取走资源
//     */
//    public void remove() {
//
//        lock.lock();
//        try {
//            if (num > 0) {
//                num--;
//                System.out.println("消费者" + Thread.currentThread().getName() + "消耗一件资源," + "当前资源池有" + num + "个");
//                pCondition.signalAll();  //唤醒等待的生产者
//            } else {
//                try {
//                    cCondition.await();
//                    //使消费者等待
//                    System.out.println(Thread.currentThread().getName() + "线程进入等待");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//}
