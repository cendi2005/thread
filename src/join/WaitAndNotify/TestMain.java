package join.WaitAndNotify;

public class TestMain {
    public static void main(String[] args) {
        Object lock = new Object();
        final Producer producer = new Producer(lock);
        final Customer customer = new Customer(lock);
        Runnable producerRunnable = new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    producer.setValue();
                }
            }
        };
        Runnable customerRunnable = new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    customer.getValue();
                }
            }
        };
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
