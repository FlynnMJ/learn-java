package com.flynn.basic.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * Created by FlynnMJ on 2017/8/1/001.
 * 1.线程池
 * 2.//TODO 理解CompletionService 内存模型
 */
public class TestExecuteService
{
    /**
     * 固定线程数线程池
     */
    public static void fixedThreadPool()
    {
        ExecutorService fixedExeService = Executors.newFixedThreadPool(3);
        Future future = null;

        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++)
        {
            list.add(new Random().nextInt(100));
        }

        try
        {
            for (Integer content : list)
            {
            /*任务提交至线程池*/
                future = fixedExeService.submit(() ->
            /*具体的任务*/
                {
                    System.out.println("当前线程: " + Thread.currentThread().getName() + ", 执行内容: " + content);
                    return content;
                });
                System.out.println("当前线程: " + Thread.currentThread().getName() + ", get: " + future.get());

            }

            for (int i = 0; i < list.size(); i++)
            {
                //System.out.println("当前线程: " + Thread.currentThread().getName() + ", get: " + future.get());
            }
        }

        catch (Exception e)
        {
            try
            {
                System.out.println("线程执行错误， " + future.get());

            }
            catch (Exception e1)
            {
                System.out.println();

            }
        }
        finally
        {
            //TODO 理解shutdown() shutdownNow()方法区别
            fixedExeService.shutdown();

        }

    }


    public static void fixedThreadPoolByCompletion()
    {
        ExecutorService fixedExeService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(fixedExeService);

        //Future future = null;

        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
        {
            list.add(new Random().nextInt(100));
        }
        System.out.println("初始值："+list);

        try
        {
            for (Integer content : list)
            {
            /*任务提交至线程池*/
                completionService.submit(() ->
            /*具体的任务*/
                {
                   // System.out.println("当前线程: " + Thread.currentThread().getName() + ", 执行内容: " + content);
                    return content;
                });
                //System.out.println("当前线程: " + Thread.currentThread().getName() + ", get: " + future.get());

            }
            List<Integer> list1=new Vector<Integer>();

            for (int i = 0; i < list.size(); i++)
            {
                Future<Integer> future = completionService.take();
                list1.add(future.get());
               // System.out.println("当前线程: " + Thread.currentThread().getName() + ", get: " + future.get());
            }
            System.out.println("返回值："+list1);

        }

        catch (Exception e)
        {

        }
        finally
        {
            //TODO 理解shutdown() shutdownNow()方法区别
            fixedExeService.shutdown();

        }

    }


    public static void main(String[] args)
    {
        // fixedThreadPool();
        fixedThreadPoolByCompletion();
    }

}

