package dateformate;

public class DateUtilTest {
    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    this.join(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    /*
                    *
                    * 每个线程都拥有自己的sdf属性
                    * **/
                    System.out.println(this.getName()+":"+ConcurrentDateUtil.parse("2013-05-24 06:02:20"));
//                    System.out.println(this.getName()+":"+DateUtil.parse("2013-05-24 06:02:20"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            new TestSimpleDateFormatThreadSafe().start();
        }

    }
}
