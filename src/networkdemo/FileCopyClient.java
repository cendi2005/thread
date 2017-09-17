package networkdemo;

import java.io.*;
import java.net.Socket;

public class FileCopyClient {
    public static void main(String[] args) throws Exception
    {
        Socket s=new Socket("127.0.0.1", 10004);

        BufferedReader buf = new BufferedReader(new FileReader("/Users/admin/Desktop/thread/src/networkdemo/1.txt"));
        String line=null;

        BufferedWriter out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        while((line=buf.readLine())!=null)
        {
            out.write(line);
            out.newLine();
            out.flush();
        }
        s.shutdownOutput();
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));

        String str=in.readLine();

        System.out.println(str);

        s.close();

        buf.close();
    }
}
