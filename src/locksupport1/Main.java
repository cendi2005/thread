package locksupport1;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<User> list = new ArrayList<User>();
    static {
        for(int i=0;i<3000000;i++){
            User tmp = new User();
            tmp.setId(i);
            tmp.setName(i+"");
            list.add(tmp);
        }
    }

    public static void main(String[] args) {

//        List<User> list = new ArrayList<User>();



        long t1 = System.currentTimeMillis();

        for(User u:list){
            if(u.getName().equalsIgnoreCase("2999999")){
                System.out.println("match the name...");
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println((t2-t1));

    }
}
