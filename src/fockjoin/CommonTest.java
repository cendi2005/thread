package fockjoin;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class CommonTest {
    public static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws Exception {


        ArrayList list = new ArrayList();
        list.add("aa");

        LinkedList l = new LinkedList();
        l.add(1);

        HashSet set = new HashSet();
        set.add(1);
        set.add(1);
        HashMap map = new HashMap();
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        map.put(4,1);
        map.put(5,1);
        map.put(6,1);
        map.put(7,1);
        map.put(8,1);
        map.put(9,1);
        map.put(10,1);
        map.put(11,1);
        map.put(12,1);
        map.put(13,1);

        System.out.println(Integer.toBinaryString(16-1));
        System.out.println(Integer.toBinaryString(32-1));
        lock.lock();
        System.out.println(1);
        lock.unlock();


    }





}
