package downloadfile;

import java.io.RandomAccessFile;

public class Util {


    public static void main(String[] args) throws Exception{


        RandomAccessFile read = new RandomAccessFile("/Users/admin/Desktop/thread/src/downloadfile/1.zip","rw");

        int filesize = (int)read.length();

        System.out.println("read file size:"+read.length());



        int threadCount = 10;
        int readSize = 0;
        if(filesize%threadCount==0){
            readSize = filesize/threadCount;
        }else{
            readSize = filesize/threadCount+1;
        }

        System.out.println("readSize:"+readSize);


        for(int i=0;i<threadCount;i++){
            FileThread fileThread = new FileThread("/Users/admin/Desktop/thread/src/downloadfile/1.pdf",i*readSize,readSize);
            fileThread.start();
        }


    }




}
