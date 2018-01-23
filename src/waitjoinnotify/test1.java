//package waitjoinnotify;
//
///**
// * wait and notify
// * 生产者和消费者问题
// *
// */
//public class test1 {
//
//    public static void main(String[] args) {
//        Resource resource = new Resource();
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
//
//}
//
//
//class ProducerThread extends Thread {
//
//    private Resource resource;
//
//    public ProducerThread(Resource resource) {
//        this.resource = resource;
//        //setName("生产者");
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
//class ConsumerThread extends Thread {
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
//    private int size = 10;
//
//    /***
//     * 向资源池中添加资源
//     */
//    public synchronized void add() {
//
//        if (num < size) {
//            num++;
//            System.out.println(Thread.currentThread().getName() + "生产一件资源,当前资源池有" + num + "个");
//            notifyAll();
//        } else {
//            try {
//                wait();
//                System.out.println(Thread.currentThread().getName() + "线程进入等待");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    /**
//     * 从资源池中取走资源
//     */
//    public synchronized void remove() {
//
//        if (num > 0) {
//            num--;
//            System.out.println(Thread.currentThread().getName() + "消耗一件资源," + "当前资源池有" + num + "个");
//            notifyAll();
//        } else {
//            try {
//                wait();
//                System.out.println(Thread.currentThread().getName() + "线程进入等待");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//
//}