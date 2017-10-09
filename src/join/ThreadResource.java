package join;

import com.sun.tools.internal.ws.api.TJavaGeneratorExtension;

public class ThreadResource {
    private int currentNum = 0;
    private int maxNum = 3;
    private Object obj = new Object();


    //消费
    public  void consume()throws Exception{

        synchronized (obj) {
            while(currentNum==0){
                obj.wait();
            }
            //不能消费
                currentNum--;
                System.out.println(Thread.currentThread().getName()+" consume one");
                obj.notifyAll();
        }
    }
    //生产

    public  void produce()throws Exception {
        //生产很慢,模拟消耗时间

        synchronized (obj) {

            if(currentNum==maxNum){
                obj.wait();
            }

            Thread.sleep(1000);
            //不能生产了
                System.out.println(Thread.currentThread().getName()+" produce one");
                currentNum++;
                obj.notifyAll();
        }
    }

}
