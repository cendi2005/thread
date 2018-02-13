package memoryreorder;

import javax.naming.event.ObjectChangeListener;

public class Test1 {
    public static void main(String[] args) {

        Object o = new Object();
        synchronized (o){
            System.out.println(1);
        }


        m();
    }

    public static synchronized void m(){
        System.out.println(2);
    }
}
