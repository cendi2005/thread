package networkdemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer1 {
    public static void main(String[] args) throws Exception {
        //port number
        int port = 7000;
        //socket
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));


        do{
            double length = dis.readDouble();
            System.out.println("服务端收到的数据长度是:"+length);
            double result = length*length;
            dos.writeDouble(result);
            dos.flush();
        }while(dis.readInt()!=0);

        socket.close();
        serverSocket.close();




    }
}
