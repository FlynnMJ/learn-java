package com.flynn.basic.thread.specialclass;

/**
 * Created by FlynnMJ on 2017/8/1/001.
 * 1.ThreadLocal 是用空间换时间，每个线程有一个备份，线程之间互不干扰
 * 2.普通加锁，是时间换空间，每个线程访问同一块资源时，等待锁资源释放。
 */
public class TestThreadLocal
{
    // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>()
    {
        public Integer initialValue()
        {
            return 0;
        }
    };


    // ②获取下一个序列值
    public int getNextNum()
    {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }


    public static void main(String[] args)
    {

        TestThreadLocal sn = new TestThreadLocal();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn,"线程t1");
        TestClient t2 = new TestClient(sn,"线程t2");
        TestClient t3 = new TestClient(sn,"线程t3");
        t1.start();
        t2.start();
        t3.start();

        if(Thread.activeCount()>1)
        {
            Thread.yield();

        }
        t2.sn.seqNum.remove();
        t2.sn.seqNum.remove();
    }


    private static class TestClient extends Thread
    {
        private TestThreadLocal sn;


        public TestClient(TestThreadLocal sn,String name)
        {
            this.sn = sn;
            this.setName(name);
        }


        public void run()
        {
            for (int i = 0; i < 3; i++)
            {
                // ④每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getNextNum() + "]");
            }
        }
    }
}

