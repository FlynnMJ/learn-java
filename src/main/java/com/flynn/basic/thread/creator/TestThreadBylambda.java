package com.flynn.basic.thread.creator;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by FlynnMJ on 2017/8/2/002.
 * Java8新特性：lambda表达式
 * 改写TestThread类
 *
 */
public class TestThreadBylambda
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
        Thread thread1 = new Thread(() -> System.out.println("通过Runnable创建线程"), "runnabletestThread");
        thread1.start();

        //③通过Callable
        FutureTask<Integer> future = new FutureTask<Integer>(() ->
        {
            System.out.println("通过Callable创建线程");
            return new Random().nextInt(100);
        });
        new Thread(future, "callabletestThread").start();

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
