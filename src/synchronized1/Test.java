package synchronized1;

public class Test {
    public static void main(String[] args) {
        synchronized (Test.class){
            m();
        }
    }
    public static synchronized void m(){
        System.out.println("hello world!");
    }
}
