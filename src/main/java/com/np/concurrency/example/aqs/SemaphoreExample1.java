package com.np.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author np
 * @date 2018/9/13
 * 信号量:并发访问控制
 *
 *
 */
public class SemaphoreExample1 {


    private static final int THREAD_NUM=20;

    public static void test(int i) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("test-"+i);

    }

    public static void main(String[] args)  {

        ExecutorService executorService=Executors.newCachedThreadPool();
        Semaphore semaphore=new Semaphore(3);

        for(int i=0;i<THREAD_NUM;i++){
            final int num=i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test(num);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }


        executorService.shutdown();
    }
}
