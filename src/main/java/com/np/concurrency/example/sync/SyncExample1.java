package com.np.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author np
 * @date 2018/9/11
 */
public class SyncExample1 {

    public synchronized void test1(int j){
        for(int i=0;i<50;i++){
            System.out.println(j+": test1-"+i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();
        SyncExample1 syncExample1=new SyncExample1();
        SyncExample1 syncExample2=new SyncExample1();
        executorService.execute(()->{
            syncExample1.test1(1);
        });
        executorService.execute(()->{
            syncExample1.test1(2);
        });
        executorService.execute(()->{
            syncExample2.test1(3);
        });
    }
}
