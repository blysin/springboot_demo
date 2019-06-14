package com.example.helloworld.demo;

/**
 * @author Blysin
 * @since 2019-05-25
 */
public class SingletonDemo {
    //添加可见性修饰符，这样某个线程初始化后可以立刻其他线程可以立刻感知
    private static volatile SingletonDemo instance;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
