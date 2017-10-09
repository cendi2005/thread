package join;

public class Client extends Thread{


    private ThreadResource resource;

    @Override
        public void run() {

        while (true) {
            try {
                resource.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Client(ThreadResource resource){
        this.resource = resource;
    }



}
