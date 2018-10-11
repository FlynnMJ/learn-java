package com.flynn.basic.thread.creator;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by FlynnMJ on 2017/8/1/001.
 * 1.创建线程必须指定线程名称（规范）
 * 2.单个线程创建的3种方式
 */
public class TestThread
{

    public static void main(String[] args)
    {
        //① 通过Thead
        Thread thread = new Thread("threadtestThread")
        {
            @Override
            public void run()
            {
                System.out.println("Thread创建线程");
            }
        };
        thread.start();

        //② 通过Runnable
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("通过Runnable创建线程");

            }
        },"runnabletestThread");
        thread1.start();

        //③通过Callable

        FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>()
        {
            @Override
            public Integer call() throws Exception
            {
                System.out.println("通过Callable创建线程");

                return new Random().nextInt(100);

            }
        });
        new Thread(future,"callabletestThread").start();

        try
        {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}
