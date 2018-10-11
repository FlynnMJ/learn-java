package com.flynn.basic.thread.method;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Flynn on 2018-10-11.
 * 1.ArrayList线程不安全操作
 * 2.使得ArrayList线程安全
 */
public class TestArrayList {
    ArrayList content = new ArrayList();
    int count = 0;

    @Test
    public void testNoSafe() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch cdl = new CountDownLatch(1000);
        for (int i = 1; i <= 1000; i++) {
            count++;
            executorService.execute(() -> {
                content.add(count);
                cdl.countDown();
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            System.err.println("error");
        }
        executorService.shutdown();
        System.out.println(content.size());//正常应该是1000，但是有时候不足1000，说明某个线程读了ArrayList脏数据
    }

    @Test
    public void testProcessRightArrayList() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch cdl = new CountDownLatch(1000);
        for (int i = 1; i <= 1000; i++) {
            count++;
            executorService.execute(() -> {
                synchronized (content) {
                    content.add(count);
                    cdl.countDown();
                }
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            System.err.println("error");
        }
        executorService.shutdown();
        System.out.println(content.size());//正常应该是1000，但是有时候不足1000，说明某个线程读了ArrayList脏数据
    }
}
