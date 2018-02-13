package volatiles;

public class Task implements Runnable {

    boolean running = true;

    int i = 0;


    @Override
    public void run() {
        while(running){
            i++;
        }
    }

    public static void main(String[] args) throws Exception{
        Task task = new Task();
        Thread th = new Thread(task);
        th.start();
        Thread.sleep(100);
        task.running = false;
        Thread.sleep(10);
        System.out.println(task.i);
        System.out.println("end");

    }


}
