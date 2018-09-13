package com.np.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author np
 * @date 2018/9/13
 *
 * 2个线程A,B交替打印自己的ID n次
 * eg:ABABABABAB
 */
public class ConditionExample {

    private static Lock lock=new ReentrantLock();
    private static AtomicBoolean isHappened=new AtomicBoolean();

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();

        Condition condition1=lock.newCondition();
        Condition condition2=lock.newCondition();

        for (int i = 0;i < 5;i++){
            executorService.execute(()->{
                lock.lock();
                try{
                    try {
                        condition2.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("B");

                    condition1.signal();
                }finally {
                    lock.unlock();
                }

            });
        }

        for (int i = 0;i < 5;i++){
            executorService.execute(()->{
                lock.lock();
                try{
                    try {

                        if(!isHappened.compareAndSet(false,true)){
                            condition1.await();
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("A");

                    condition2.signal();
                }finally {
                    lock.unlock();
                }

            });
        }

        executorService.shutdown();
    }

}
