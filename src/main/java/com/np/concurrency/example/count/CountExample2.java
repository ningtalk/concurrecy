package com.np.concurrency.example.count;

import com.np.concurrency.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author np
 * @date 2018/9/11
 */
@ThreadSafe
public class CountExample2 {

    private static AtomicInteger COUNT=new AtomicInteger(0);

    //请求总数
    private static int clientTotal=5000;
    //同时并发执行的线程数
    private static int threadTotal=200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:"+COUNT);

    }


    private static void add(){

        COUNT.incrementAndGet();
    }

}
