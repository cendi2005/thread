package package8;

/**
 * Created by admin on 2017/6/24.
 */
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestExchanger {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger<String>();
        executor.execute(new Runnable() {
            String data1 = "abc";
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() +"正在把数据 "+ data1+ " 交换出去" );
                    Thread.sleep((long) (Math.random()*1000));

                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName() + "交换数据 到  "+ data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.execute(new Runnable() {
            String data1 = "def";
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() +"正在把数据 "+ data1+ " 交换出去" );
                    Thread.sleep((long) (Math.random()*1000));

                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName() + "交换数据 到  "+ data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}