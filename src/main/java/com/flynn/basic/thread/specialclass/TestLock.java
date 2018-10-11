package com.flynn.basic.thread.specialclass;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Flynn on 2018-10-11.
 * 同步手段（多个线程访问共享资源的时候，需要同步手段，避免线程访问脏数据）
 * 1.1.7jdk之前推荐使用Lock而不是用Synchronized，之后版本皆可
 * 2.相比Synchronized lock 需要手动释放锁
 */
public class TestLock {
    ReentrantLock reentrantLock = new ReentrantLock();
    Integer count1 = 0;


    /**
     * 锁住当前对象
     *
     * @throws Exception
     */
    @Test
    public void testSum() throws Exception {
        Thread t1 = new Thread(() -> {
            sum();
        });
        Thread t2 = new Thread(() -> {
            sum();
        });
        t1.start();
        t2.start();
        //主线程等待两个线程执行完才执行 建议用join() 用sleep()太粗暴了
        t1.join();
        t2.join();
        System.out.println("last:" + count1);
    }


    public void sum() {
        for (int i = 1; i <= 1000; i++) {
            try {
                reentrantLock.lock();
                count1++;
                System.out.println("当前线程：" + Thread.currentThread().getName() + ", " + count1);
            } catch (Exception e) {
                System.out.println("error");
            } finally {
                reentrantLock.unlock();
            }
        }

    }
}
