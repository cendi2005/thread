package join.WaitAndNotify;

public class LockWaitAndNotifyDemoMain {
    public static void main(String[] args) {
        final LockWaitAndNotifyDemo td = new LockWaitAndNotifyDemo();
        Runnable producerRunnable = new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < Integer.MAX_VALUE; i++)
                    td.set();
            }
        };
        Runnable customerRunnable = new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < Integer.MAX_VALUE; i++)
                    td.get();
            }
        };
        Thread ProducerThread = new Thread(producerRunnable);
        ProducerThread.setName("Producer");
        Thread ConsumerThread = new Thread(customerRunnable);
        ConsumerThread.setName("Consumer");
        ProducerThread.start();
        ConsumerThread.start();
    }
}
