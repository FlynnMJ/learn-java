package com.flynn.basic.thread.keyword;

/**
 * Created by FlynnMJ on 2017/8/1/001.
 * 1.volatile 告诉线程不要从工作内存取数据，直接从物理内存取数据，不能保证线程安全
 */
public class TestVolitile
{
    volatile static int num = 0;


    static synchronized void increase()
    {
        num += 1;
        System.out.println("当前线程："+Thread.currentThread().getName()+", num="+num);
    }


    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            new Thread("计算线程"+i)
            {
                @Override
                public void run()
                {
                    for (int j = 0; j < 1000; j++)
                    {
                        increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount()>1)
        {
            Thread.yield();
        }
        System.out.println("线程数： "+Thread.activeCount());
        System.out.println("最后结果num: "+num);
    }
}
