package join;

class Resource{
    private String name;
    private int count;
    private boolean flag = false;

    public synchronized void set(String name){
        while(flag){//将if语句变为while语句，使得线程被唤醒后，能够再次进行flag的判断
            try{
                this.wait();
            }
            catch(InterruptedException e){

            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name + count ;
        count++;
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);

        flag = true;
        notifyAll();//仅仅是notify()，可能会唤醒同一个锁上的线程，从而出现死锁现象

    }

    public synchronized void out(){
        while(!flag){//将if语句变为while语句，使得线程被唤醒后，能够再次进行flag的判断
            try{
                this.wait();
            }
            catch(InterruptedException e){

            }
        }
        System.out.println(Thread.currentThread().getName()+".......消费者.."+this.name);

        flag = false;
        notifyAll();//仅仅是notify()，可能会唤醒同一个锁上的线程，从而出现死锁现象
    }
}

class Producer implements Runnable{
    private Resource r;

    Producer(Resource r){
        this.r = r;
    }
    public void run(){
        while(true){
            r.set("烤鸭");
        }
    }
}

class Consumer implements Runnable{
    private Resource r;

    Consumer(Resource r){
        this.r = r;
    }

    public void run(){
        while(true){
            r.out();
        }
    }
}

public class ProConTest{
    public static void main(String [] args){
        Resource r = new Resource();

        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        Thread t0 = new Thread(pro);//多生产者，多消费者
        Thread t1 = new Thread(pro);

        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        Thread t4 = new Thread(con);
        Thread t5 = new Thread(con);
        Thread t6 = new Thread(con);


        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        /*
        多生产者多消费者可能出现的问题：
        1.生产了，但是没有被消费：（多生产）

            Thread-0...生产者...烤鸭697
            Thread-1...生产者...烤鸭698
            Thread-2.......消费者..烤鸭698
            Thread-0...生产者...烤鸭699
            Thread-1...生产者...烤鸭700
            Thread-2.......消费者..烤鸭700

        2.生产一个，被多次消费：（多消费）

            Thread-1...生产者...烤鸭387
            Thread-3.......消费者..烤鸭387
            Thread-2.......消费者..烤鸭387
            Thread-1...生产者...烤鸭388
            Thread-3.......消费者..烤鸭388
            Thread-2.......消费者..烤鸭388
        */
    }
}