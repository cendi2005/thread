package join;

public class ProducerThread implements Runnable {
    private PublicResource resource;

    public ProducerThread(PublicResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.increace();
        }
    }
}