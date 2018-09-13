package com.np.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author np
 * @date 2018/9/13
 * CountDownLatch
 * 1、阻塞线程，等待执行完成后继续执行
 * 2、可以设置等待时间
 *
 */
public class CountDownLatchExample1 {

    private static final int THREAD_NUM=200;

    public static void test(int i) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("test-"+i);

    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService=Executors.newCachedThreadPool();
        CountDownLatch countDownLatch=new CountDownLatch(THREAD_NUM);

        for(int i=0;i<THREAD_NUM;i++){
            final int num=i;
            executorService.execute(()->{
                try {
                    test(num);
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        /**
         *
         * countDownLatch.await();
         */


        //设置等待时间
        countDownLatch.await(1,TimeUnit.MICROSECONDS);
        System.out.println("final");
        executorService.shutdown();
    }
}
