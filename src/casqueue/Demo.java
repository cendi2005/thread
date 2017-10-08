package casqueue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Demo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.add("hello");
    }
}
