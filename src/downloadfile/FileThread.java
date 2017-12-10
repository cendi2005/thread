package downloadfile;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;

public class FileThread extends Thread{
    private String filePath;
    private int seekSize;
    private int canReadSize;

    public FileThread(String filePath,int seekSize,int canReadSize){
        this.filePath = filePath;
        this.seekSize = seekSize;
        this.canReadSize = canReadSize;
    }

    int hasRead = 0;
    int totalHasRead = 0;

    @Override
    public void run() {
        //file
        try {

            //file position
            RandomAccessFile file = new RandomAccessFile(filePath,"rw");
            RandomAccessFile store = new RandomAccessFile("/Users/admin/Desktop/thread/src/downloadfile/2.pdf","rw");

            //seek the unread byte
            file.seek((long)seekSize);
            store.seek((long)seekSize);

            System.out.println(Thread.currentThread().getName()+" start at position "+seekSize);
            System.out.println(Thread.currentThread().getName()+" can read canReadSize is  "+canReadSize);

            //byte cache
            byte[] buffer = new byte[59978];



            while(totalHasRead<canReadSize&&((hasRead=file.read(buffer))!=-1)) {
                System.out.println();
                store.write(buffer,0,hasRead);
                totalHasRead += hasRead;
                System.out.println(Thread.currentThread().getName() + " totalHasRead " + totalHasRead);
            }

            //close stream
            store.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
