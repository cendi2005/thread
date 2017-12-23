package FutureTask;

import java.util.concurrent.*;

public class sync4_7 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();
        ResultTask t1 = new ResultTask(new Task("小强"));
        es.submit(t1);
        ResultTask t2 = new ResultTask(new Task("小小强"));
        es.submit(t2);

//        ResultTask t3 = new ResultTask(new Task2("循环任务，测试任务在正常执行中，会不会被强制取消"));
//        es.submit(t3);

        TimeUnit.SECONDS.sleep(1);  //主线程休眠1秒，然后取消t2任务
        t2.cancel(true); // 演示1：观察被取消的任务在调用done() 方法中的打印信息,true 和 false 的区别是：true:在休眠中，将被强制取消，不会继续下面的代码，而false，当前任务会执行完，只是获取结果会爬出异常
//        t3.cancel(true);
        es.shutdown();


        System.out.println("----------------  获取任务结果");
        System.out.println(t1.get());
        System.out.println(t2.get());  //演示2：未正常完成的任务，调用获取结果会抛出异常CancellationException
    }
}

/**
 * 继承FutureTask，
 */
class ResultTask extends FutureTask<String>{

    public ResultTask(Callable<String> callable) {
        super(callable);
    }

    public ResultTask(Runnable runnable, String result) {
        super(runnable, result);
    }

    //当此任务转换到状态 isDone（不管是正常地还是通过取消）时该方法会被调用。
    @Override
    protected void done() {
        System.out.println(Thread.currentThread().getName() + ",是否在正常完成前被取消：" + isCancelled());
    }
}

class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        long time = (long)(Math.random()*10);
        System.out.printf("%s,%s,执行前，该任务将耗时:%s秒\n", Thread.currentThread().getName(), name, time);
        TimeUnit.SECONDS.sleep(time);  //随机休眠，让外面能有时间执行取消任务的代码
        System.out.printf("%s,%s,执行过了，耗时:%s秒\n", Thread.currentThread().getName(), name, time);
        return name;
    }
}
// 演示3 使用的类：正常的任务，不会被强制取消，只有线程等待获取中断会被取消
class Task2 implements Callable<String> {
    private String name;

    public Task2(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {
        boolean flag = true;
        while (flag){
            System.out.printf("%s,循环中\n",Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);  //演示3：正常的任务，不会被强制取消，只有线程等待获取中断会被取消
        }
        return name;
    }
}