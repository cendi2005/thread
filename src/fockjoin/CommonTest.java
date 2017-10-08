package fockjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class CommonTest {
    public static void main(String[] args) throws Exception
    {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Long> result = pool.submit(new Sum(0L, 10000L));
//        pool.shutdown();
        System.out.println(result.get());

    }
}
