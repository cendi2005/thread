package JUC;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by admin on 2017/5/21.
 * http://www.cnblogs.com/eoss/p/6108791.html
 */
public class CyclicBarrier1 {



    //处理的业务逻辑结果
    private static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) {
        /**
         * CyclicBarrier会阻塞5个线程，当5个线程都到达屏障时会优先执行Action的run()方法
         */
        CyclicBarrier c = new CyclicBarrier(5,new Action());
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                /**
                 * 将计算完成的结果放入Map中
                 */
                int sleepTime = new Random().nextInt(1000);


                try {
                    Thread.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName()+"--->处理线程耗时"+sleepTime+"后结束任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                System.out.println();
                map.put(String.valueOf(Thread.currentThread().getId()), new Random().nextInt(10));
                try {
                    /**
                     * 被屏障拦截
                     */
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 屏障开启后，优先执行Action的run()方法合并结果
     */
    private static class Action implements Runnable{

        @Override
        public void run() {
            int j=0;
            for (Map.Entry<String,Integer> entry : map.entrySet()){
                System.out.println(entry.getValue());
                j = j+entry.getValue();
            }
            System.out.println("j="+j);
        }
    }
}
