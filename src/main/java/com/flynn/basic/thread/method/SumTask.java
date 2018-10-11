package com.flynn.basic.thread.method;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by FlynnMJ on 2017/7/27/027.
 */
public class SumTask extends RecursiveTask<Long>
{
    static final int THRESHOLD = 2000000;
    long[] array;
    int start;
    int end;


    SumTask(long[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute()
    {
        if (end - start <= THRESHOLD)
        {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++)
            {
                sum += array[i];
            }
            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e)
            {
            }
            System.out.println(String.format("compute %d~%d = %d", start, end, sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);
        invokeAll(subtask1, subtask2);
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }


    static void fillRandom(long[] array)
    {
        Random rand = new Random(25);

        for (int i = 0; i < array.length; i++)
        {
            array[i] = Long.valueOf(rand.nextInt(100));
            //System.out.println(array[i]);
        }
    }


    static void singleThread(long[] array)
    {
        long sum = 0L;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++)
        {
            sum += array[i];
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Fork/join sum: " + sum + " in " + (endTime - startTime) + " ms.");

        System.out.println(sum);
    }


    static void muiltiThread(long[] array)
    {
        // fork/join task:
        ForkJoinPool fjp = new ForkJoinPool(10); // 最大并发数4
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }


    public static void main(String[] args) throws Exception
    {
        // 创建随机数组成的数组:
        long[] array = new long[4000000];
        fillRandom(array);
        //①调用单线程处理
        singleThread(array);
        //②调用多线程处理
        muiltiThread(array);

    }
}
