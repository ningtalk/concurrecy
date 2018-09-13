package com.np.concurrency.example.aqs;

import java.util.concurrent.*;

/**
 * @author np
 * @date 2018/9/13
 */
public class CyclicBarrierExample1 {


    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
    private static int THREAD_NUM=20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService=Executors.newCachedThreadPool();

        for(int i=0 ; i<THREAD_NUM ;i++){
            final int num=i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(num);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }

            });
        }

        executorService.shutdown();



    }

    public static void race(int i) throws BrokenBarrierException, InterruptedException, TimeoutException {
        System.out.println(i+" is ready.");
        cyclicBarrier.await(3000,TimeUnit.MILLISECONDS);
        Thread.sleep(100);
        System.out.println(i+" continue.");

    }

}
