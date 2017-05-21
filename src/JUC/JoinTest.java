package JUC;

/**
 * Created by admin on 2017/5/20.
 */
// JoinTest.java的源码
public class JoinTest{

    public static void main(String[] args){
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”
            t1.start();                     // 启动“线程t1”
            t1.join();                        // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”

            ThreadA t2= new ThreadA("t2"); // 新建“线程t1”
            t2.start();                     // 启动“线程t1”
            t2.join();                        // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”


            ThreadA t3 = new ThreadA("t3"); // 新建“线程t1”
            t3.start();                     // 启动“线程t1”
            t3.join();                        // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            System.out.printf("%s finish\n", Thread.currentThread().getName());


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name){
            super(name);
        }
        public void run(){
            System.out.printf("%s start\n", this.getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("不管怎么样，执行完嘞");
            }

            System.out.printf("%s finish\n", this.getName());
        }
    }
}