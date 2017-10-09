package join.WaitAndNotify;

import java.io.IOException;

public class WaitAndNotify
{
    public static void main(String[] args) throws IOException
    {
        Person person = new Person();
        new Thread(new Consumer("消费者一", person)).start();
        new Thread(new Consumer("消费者二", person)).start();
        new Thread(new Consumer("消费者三", person)).start();

        new Thread(new Producer("生产者一", person)).start();
        new Thread(new Producer("生产者一", person)).start();
        new Thread(new Producer("生产者一", person)).start();

    }
}

class Producer implements Runnable
{
    private Person person;
    private String producerName;

    public Producer(String producerName, Person person)
    {
        this.producerName = producerName;
        this.person = person;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                person.produce();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}

class Consumer implements Runnable
{

    private Person person;
    private String consumerName;

    public Consumer(String consumerName, Person person)
    {
        this.consumerName = consumerName;
        this.person = person;
    }

    @Override
    public void run()
    {
        try
        {
            person.consume();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}

class Person
{
    private int foodNum = 0;
    private Object synObj = new Object();

    private final int MAX_NUM = 5;

    public void produce() throws InterruptedException
    {
        synchronized (synObj)
        {
            if (foodNum == 5)
            {
                System.out.println("box is full，size = " + foodNum);
                synObj.wait();
            }
            foodNum++;
            System.out.println(Thread.currentThread().getName()+",produce success foodNum = " + foodNum);
            synObj.notifyAll();
        }

    }

    public void consume() throws InterruptedException
    {
        synchronized (synObj)
        {
            if (foodNum == 0)
            {
                System.out.println("box is empty,size = " + foodNum);
                synObj.wait();
            }
            foodNum--;
            System.out.println("consume success foodNum = " + foodNum);
            synObj.notifyAll();
        }

    }
}