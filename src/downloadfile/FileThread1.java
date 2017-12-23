package downloadfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class FileThread1 implements Callable<Long> {
    private String filePath;
    private long seekSize;
    private long canReadSize;

    public FileThread1(String filePath, long seekSize, long canReadSize){
        this.filePath = filePath;
        this.seekSize = seekSize;
        this.canReadSize = canReadSize;
    }

    int hasRead = 0;
    int totalHasRead = 0;

    @Override
    public Long call() throws Exception {
        long length = 0;

        //file position
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        RandomAccessFile store = new RandomAccessFile("/Users/admin/Desktop/thread/src/downloadfile/2.zip","rw");

        //seek the unread byte
        file.seek((long)seekSize);
        store.seek((long)seekSize);

        System.out.println(Thread.currentThread().getName()+" start at position "+seekSize);
        System.out.println(Thread.currentThread().getName()+" can read canReadSize is  "+canReadSize);

        //byte cache
        byte[] buffer = new byte[1024*1024];



        long start = System.currentTimeMillis();
        while(totalHasRead<canReadSize&&((hasRead=file.read(buffer))!=-1)) {
            store.write(buffer,0,hasRead);
            totalHasRead += hasRead;
            length += hasRead;
        }
        long end = System.currentTimeMillis();

        System.out.println("therad " + Thread.currentThread().getName() +" use time is "+(end-start));
        System.out.println(Thread.currentThread().getName() + " totalHasRead " + totalHasRead);

        //close stream
        store.close();
        return length;
    }
}
