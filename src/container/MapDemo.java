package container;

import java.util.HashMap;
import java.util.UUID;

public class MapDemo {
    static final HashMap<String, String> map = new HashMap<String, String>(2);

    public static void main(String[] args) {
        System.out.println("start");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                            System.out.println(map);
                        }
                    }, "moon" + i).start();
                }
            }
        }, "ftf");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
