package join;

public class Test {
    public static void main(String[] args) {

        ThreadResource resource = new ThreadResource();


        Client c1 = new Client(resource);
        Client c2 = new Client(resource);
        Client c3 = new Client(resource);

        Server s1 = new Server(resource);
        Server s2 = new Server(resource);

        s1.start();
        s2.start();


        c1.start();
        c2.start();
        c3.start();




    }
}
