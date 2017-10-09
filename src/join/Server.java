package join;

public class Server  extends Thread{
    private ThreadResource resource;


    public Server(ThreadResource resource){
        this.resource = resource;

    }


    @Override
    public void run() {

        while (true) {
            try {
                resource.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
