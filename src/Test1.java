import package4.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test1 {
    public static void main(String[] args) {



    Node data = new Node();
    data.item = 1;
    data.next = null;
    Node head,tail;
    head = tail = new Node();

        tail.next = data;
        tail = data;
        System.out.println(head.next.toString());
        System.out.println(data.toString());
        System.out.println(tail.toString());







    }


    static class Node{

        int item;
        Node next;
    }

}