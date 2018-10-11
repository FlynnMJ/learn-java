package com.flynn.basic.thread.keyword;

import com.flynn.basic.thread.object.ShareObject;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by Flynn on 2018-10-11.
 * 1.三种
 * 对象锁：synchronized修饰具体的类的实例对象，或者修饰类的一个方法
 * 方法锁：synchronized修饰类的一个方法
 * 类锁：synchronized修饰一个类的静态方法，或者修饰诸如(Object.class)
 * <p>
 * 2.synchronized锁方法相当于锁住对象
 * <p>
 * 3.synchronized锁住成员变量不生效，只能锁对象
 */
public class TestSynchronized {
    Integer count1 = 0;
    static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    ShareObject shareObject = new ShareObject();


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

    /**
     * 锁住其他对象
     *
     * @throws Exception
     */
    @Test
    public void testShareSum() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //闭锁CountDownLatch
        CountDownLatch cdl = new CountDownLatch(1000);
        for (int i = 1; i <= 1000; i++) {
            executorService.execute(() -> {
                synchronized (shareObject) {
                    shareObject.count2++;
                    System.out.println("当前线程：" + Thread.currentThread().getName() + ",当前值：" + shareObject.count2);
                    cdl.countDown();
                }

            });
        }
        cdl.await();
        executorService.shutdown();

        System.out.println("last:" + shareObject.count2);
    }


    public void sum() {

        synchronized (count1) {
            for (int i = 1; i <= 1000; i++) {
                count1++;
                System.out.println("当前线程：" + Thread.currentThread().getName() + ", " + count1);
            }
        }

    }

    public static void main(String[] args) throws Exception {
    }
}
