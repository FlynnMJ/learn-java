package com.flynn.basic.thread.method;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by FlynnMJ on 2017/7/28/028.
 * 1.Join  执行完该线程再执行其他线程
 */
public class TestJoin implements Runnable
{
    @Override
    public void run()
    {
        System.out.printf("Beginning data sources loading: %s\n", new
                Date());
        try
        {
            TimeUnit.SECONDS.sleep(4);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.printf("Data sources loading has finished: %s\n", new Date());
    }


    public static void main(String[] args) throws InterruptedException
    {
        TestJoin dsLoader = new TestJoin();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");
        Thread thread2 = new Thread(dsLoader, "NetworkConnectionLoader");

        thread1.start();
        thread2.start();

        // thread1.join();
        //thread2.join();

        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
