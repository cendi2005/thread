package networkdemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileCopyServer {
    public static void main(String[] args) throws Exception
    {
        ServerSocket ss=new ServerSocket(10004);
        Socket s=ss.accept();
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line=null;

        BufferedWriter buf=new BufferedWriter(new FileWriter("/Users/admin/Desktop/thread/src/networkdemo/2.txt"));
        while((line=in.readLine())!=null)
        {
            buf.write(line);
            buf.newLine();
            buf.flush();
        }
        PrintWriter out=new PrintWriter(s.getOutputStream(),true);
        out.println("传输成功！");
        ss.close();
        buf.close();
    }
}
