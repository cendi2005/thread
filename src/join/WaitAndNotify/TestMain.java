package join.WaitAndNotify;

public class TestMain {
    public static void main(String[] args) {
        Object lock = new Object();
//        final Producer producer = new Producer(lock);
//        final Customer customer = new Customer(lock);
                final Producer producerRunnable = new Producer(lock);
        final Customer customerRunnable = new Customer(lock);
//        Runnable producerRunnable = new Runnable()
//        {
//            public void run()
//            {
//                while (true)
//                {
//                    producer.setValue();
//                }
//            }
//        };
//        Runnable customerRunnable = new Runnable()
//        {
//            public void run()
//            {
//                while (true)
//                {
//                    customer.getValue();
//                }
//            }
//        };
        /**
         * 多个线程轮流生产，但资源永远只会出现一份，被某个Lock占有
         *
         * */
        Thread producerThread = new Thread(producerRunnable);
        Thread CustomerThread = new Thread(customerRunnable);

        Thread producerThread1 = new Thread(producerRunnable);
        Thread CustomerThread1 = new Thread(customerRunnable);

        Thread producerThread2 = new Thread(producerRunnable);
        Thread CustomerThread2 = new Thread(customerRunnable);

        producerThread.start();
        CustomerThread.start();

        producerThread1.start();
        CustomerThread1.start();

        producerThread2.start();
        CustomerThread2.start();


    }
}
