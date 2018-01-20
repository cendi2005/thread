package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by admin on 2017/5/3.
 */
public class CyclicBarrierDemo {

    public static class Solider implements Runnable{

        private String solider;
        private final CyclicBarrier cyclic;

         Solider(CyclicBarrier cyclic,String soliderName) {
            this.cyclic = cyclic;
            this.solider = soliderName;
        }


        @Override
        public void run() {
             //等待所有士兵到齐
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }


        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(solider+"mission complete....");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag,int N) {
            this.flag = flag;
            this.N = N;
        }


        @Override
        public void run() {
          if(flag){
              System.out.println("siling shibing"+N+"ge.mission complete");
          }else{
              System.out.println("siling shibing"+N+"ge.together complete");
              flag = true;
          }
        }
    }

    public static void main(String[] args) {
        final int N = 3;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));
        //设置屏障点，主要是为了执行这个方法
        for(int i=0;i<N;i++){
            System.out.println("shibing"+i+"baodao");
            allSoldier[i] = new Thread(new Solider(cyclic,"shibing"+i));
            allSoldier[i].start();
        }
    }


}
