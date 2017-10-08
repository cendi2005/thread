package fockjoin;

import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Long>
{

    /**
     * {变量说明}
     */
    private static final long serialVersionUID = 1L;
    private static final Long THRESHOLD = 100L;
    private Long start;
    private Long end;

    public Sum(Long start, Long end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute()
    {
        Long sum = 0L;
        if ((end - start) < THRESHOLD)
        {
            for (Long i = start; i <= end; i++)
            {
                sum += i;
            }
        } else
        {
//            System.out.println("fork");
            Long middle = (start + end) / 2;
            Sum left = new Sum(start, middle);
            Sum right = new Sum(middle + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }
        return sum;
    }

}