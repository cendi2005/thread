package join;

public class Join {
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 3; i++) {
// 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), "Thread- "+ String.valueOf(i));
            thread.start();
            previous = thread;
        }
        System.out.println(Thread.currentThread().getName() + " terminate. main");
    }
    static class Domino implements Runnable {
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }
        public void run() {
            try {
                System.out.println("dimino his parent thread is " +Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println("subthread.."+Thread.currentThread().getName() + " terminate.");
        }
    }
}
