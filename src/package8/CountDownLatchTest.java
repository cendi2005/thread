package package8;import java.util.concurrent.CountDownLatch;/** *  */public class CountDownLatchTest {    static CountDownLatch c = new CountDownLatch(2);    public static void main(String[] args) throws Exception {        new Thread(new Runnable() {            @Override            public void run() {                try {                    Thread.sleep(1000);                } catch (InterruptedException e) {                    e.printStackTrace();                }                System.out.println(1);                c.countDown();                System.out.println(2);                c.countDown();            }        }).start();        c.await();        System.out.println("3");    }}