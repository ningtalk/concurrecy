package com.np.concurrency.example.aqs;

import com.np.concurrency.annotations.NotThreadSafe;
import com.np.concurrency.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author np
 * @date 2018/9/13
 */
public class LockExample {

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();

        for (int i=0 ; i<10 ;i++){
            executorService.execute(new TicketLock());
        }

        executorService.shutdown();
    }


    @NotThreadSafe
    static class Ticket implements Runnable{

        private static  int tick=10;

        @Override
        public void run() {
            while (true){
                if(tick>0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"-完成售票，剩余:"+--tick);
                }

            }
        }
    }


    @ThreadSafe
    static class TicketLock implements Runnable{

        private static int tick=10;

        private static Lock lock=new ReentrantLock();

        @Override
        public void run() {
            while (true){
                saleTicket();

            }
        }
        public static void saleTicket(){
            lock.lock();
            try{
                if(tick>0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"-完成售票，剩余:"+--tick);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
