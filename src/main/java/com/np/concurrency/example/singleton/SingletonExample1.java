package com.np.concurrency.example.singleton;

/**
 * @author np
 * @date 2018/9/11
 * 懒汉模式
 */
public class SingletonExample1 {


    private volatile SingletonExample1 instance;

    private SingletonExample1(){

    }

    public SingletonExample1 getInstance(){
        if(null==instance){
            synchronized (SingletonExample1.class){
                if(null==instance){
                    instance=new SingletonExample1();
                }
            }
        }

        return instance;
    }

}
