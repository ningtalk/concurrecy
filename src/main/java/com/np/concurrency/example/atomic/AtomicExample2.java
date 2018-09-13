package com.np.concurrency.example.atomic;


import java.util.concurrent.atomic.AtomicReference;

/**
 * @author np
 * @date 2018/9/11
 */
public class AtomicExample2 {

    public static void main(String[] args) {
        AtomicExample2 atomicExample21=new AtomicExample2();
        AtomicExample2 atomicExample22=new AtomicExample2();
        AtomicReference ar=new AtomicReference(atomicExample21);
        ar.compareAndSet(atomicExample21,atomicExample22);

        AtomicExample2 atomicExample23= (AtomicExample2) ar.get();

        System.out.println(atomicExample21.hashCode());
        System.out.println(atomicExample22.hashCode());
        System.out.println(atomicExample23.hashCode());
    }

}
