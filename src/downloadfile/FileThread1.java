package downloadfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class FileThread1 implements Callable<Integer> {
    private String filePath;
    private int seekSize;
    private int canReadSize;

    public FileThread1(String filePath, int seekSize, int canReadSize){
        this.filePath = filePath;
        this.seekSize = seekSize;
        this.canReadSize = canReadSize;
    }

    int hasRead = 0;
    int totalHasRead = 0;

    public void run() {
        //file
        try {

            //file position
            RandomAccessFile file = new RandomAccessFile(filePath,"rw");
            RandomAccessFile store = new RandomAccessFile("/Users/admin/Desktop/thread/src/downloadfile/2.txt","rw");

            //seek the unread byte
            file.seek((long)seekSize);
            store.seek((long)seekSize);

            System.out.println(Thread.currentThread().getName()+" start at position "+seekSize);
            System.out.println(Thread.currentThread().getName()+" can read canReadSize is  "+canReadSize);
            //byte cache
//            byte[] buffer = new byte[1024*1024];
            byte[] buffer = new byte[3];

            long start = System.currentTimeMillis();
            while(totalHasRead<canReadSize&&((hasRead=file.read(buffer))!=-1)) {
                System.out.println();
                store.write(buffer,0,hasRead);
                totalHasRead += hasRead;
                System.out.println(Thread.currentThread().getName() + " totalHasRead " + totalHasRead);
            }
            long end = System.currentTimeMillis();

            System.out.println("therad " + Thread.currentThread().getName() +" use time is "+(end-start));

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

    @Override
    public Integer call() throws Exception {
        int length = 0;

        //file position
        RandomAccessFile file = new RandomAccessFile(filePath,"rw");
        RandomAccessFile store = new RandomAccessFile("/Users/admin/Desktop/thread/src/downloadfile/2.png","rw");

        //seek the unread byte
        file.seek((long)seekSize);
        store.seek((long)seekSize);

        System.out.println(Thread.currentThread().getName()+" start at position "+seekSize);
        System.out.println(Thread.currentThread().getName()+" can read canReadSize is  "+canReadSize);

        //byte cache
        byte[] buffer = new byte[3];



        long start = System.currentTimeMillis();
        while(totalHasRead<canReadSize&&((hasRead=file.read(buffer))!=-1)) {
            System.out.println();
            store.write(buffer,0,hasRead);
            totalHasRead += hasRead;
            length += hasRead;
            System.out.println(Thread.currentThread().getName() + " totalHasRead " + totalHasRead);
        }
        long end = System.currentTimeMillis();

        System.out.println("therad " + Thread.currentThread().getName() +" use time is "+(end-start));
        //close stream
        store.close();
        return length;
    }
}
