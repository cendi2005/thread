package blockqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class BlockingQueue_Study {
    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(5); // <队列对象String>(队列容量)

    List<String> list = Arrays.asList("list1", "list2");

    /**
     * 插入操作：插入元素为null时将抛出NullPointerException.
     *
     */
    @Test
    public void BlockingQueueInsertTest() {
        // add-AbstractQueue
        boolean add = blockingQueue.add("add"); // 插入成功：true；阻塞队列已满：抛出java.lang.IllegalStateException: Queue full
        System.out.println("add:" + add);
        boolean offer = blockingQueue.offer("offer"); // 插入成功:true；队列已满:false；
        System.out.println("offer:" + offer);
        try {
            blockingQueue.put("put"); // 无返回值；一直阻塞直到插入成功；线程被中断即Thread.interrupted()时：抛出InterruptedException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            boolean offer_timeout = blockingQueue.offer("offer_timeout", 3, TimeUnit.SECONDS); // 插入成功：true；插入超时：false；线程被中断即Thread.interrupted()时：抛出InterruptedException
            System.out.println("offer_timeout:" + offer_timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean addAll = false;
        try {
            // addAll-AbstractQueue
            addAll = blockingQueue.addAll(list); // 插入成功：true；内部结构为foreach遍历add(e)，所以可能部分数据插入成功，部分插入失败
        } catch (Exception e) { // 吃掉异常
            System.err.println("addAll_Exception:" + e.getMessage());
            // e.printStackTrace();
        }
        System.out.println("addAll:" + addAll);
        System.out.println(blockingQueue.toString());
    }

    /**
     * 阻塞队列移除操作.
     *
     */
    @Test
    public void BlockingQueueRemoveTest() {
        BlockingQueueInsertTest();
        // remove-AbstractQueue
        String remove = blockingQueue.remove(); // 移除队列头。移除成功：返回被移除的元素；队列为空：抛出NoSuchElementException
        System.out.println("remove:" + remove);
        boolean removeObj = blockingQueue.remove("offer"); // 用equals()判断两个对象是否相等。移除成功：true；不存在该元素：false
        System.out.println("removeObj:" + removeObj);
        String poll = blockingQueue.poll(); // 移除队列头。和remove()的唯一区别在于，当队列为空返回null而不是抛异常
        System.out.println("poll:" + poll);
        try {
            String poll_timeout = blockingQueue.poll(3, TimeUnit.SECONDS); // 移除成功：返回被移除的元素；移除超时（一直无可移除的对象）：返回null；线程被中断抛InterruptedException
            System.out.println("poll_timeout:" + poll_timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String take = blockingQueue.take(); // 一直阻塞，直到取回数据；等待时被中断则抛出InterruptedException
            System.out.println("take:" + take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blockingQueue.add("list1");
        boolean removeAll = blockingQueue.removeAll(list); // Iterator<?> it = iterator();迭代remove()，只要remove成功一个且不抛异常就返回false
        System.out.println("removeAll:" + removeAll);
        System.out.println(blockingQueue.toString());
        blockingQueue.addAll(list);
        Predicate<? super String> filter = (p) -> p.startsWith("list");
        boolean removeIf = blockingQueue.removeIf(filter); // JDK8可用；remove成功任何一个元素即返回true
        System.out.println("removeIf:" + removeIf);
        System.out.println(blockingQueue.toString());
        //
        System.out.println("数据保留");
        BlockingQueueInsertTest();
        // retailAll-AbstractCollection
        boolean retainAll = blockingQueue.retainAll(list); // 队列因此请求而改变则返回true，没有remove任何一个元素即返回false
        System.out.println("retainAll:" + retainAll);
        System.out.println(blockingQueue.retainAll(Arrays.asList("retailAll")));
        System.out.println(blockingQueue.retainAll(list)); // 没有remove任何一个元素即返回false
        System.out.println(blockingQueue.toString());
        blockingQueue.clear(); // 移除所有元素，clear首先加锁fullyLock()
    }

    /**
     * 阻塞队列的cheek：element()/peek().
     *
     */
    @Test
    public void BlockingQueueCheckTest() {
        BlockingQueueInsertTest();
        String element = blockingQueue.element(); // 取回但不移除队首元素，队列为空throw NoSuchElementException
        System.out.println("element:" + element);
        String peek = blockingQueue.peek(); // 取回但不移除队首元素，队列为空返回null
        System.out.println("peek:" + peek);
        boolean contains = blockingQueue.contains("add"); // 有一元素equals即返回true
        System.out.println("contains:" + contains);
        boolean containsAll = blockingQueue.containsAll(Arrays.asList("add", "offerNew")); // contains所有元素即返回true
        System.out.println("containsAll:" + containsAll);
        boolean isEmpty = blockingQueue.isEmpty(); // 队列无元素即返回true
        System.out.println("isEmpty:" + isEmpty);
    }


}