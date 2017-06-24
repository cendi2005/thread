import java.util.concurrent.locks.LockSupport;

public class Test {



    public static void main(String[] args) {

        User u = null;


        User u1 = new User();
        u1.setAge(10);


        u = u1;
        u1 = null;
        System.out.println(u.getAge());
        System.out.println(u.toString());




    }


    static class User{
        public int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String name;
    }

}