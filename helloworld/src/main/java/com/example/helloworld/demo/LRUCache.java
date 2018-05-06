package com.example.helloworld.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 一个简单的LRU策略缓存
 * 实现思想：
 * 1、基础已ConcurrentHashMap实现、、、应该用队列，检查过期时间时从栈底获取数据，并判断
 * 2、当缓存数量超过容量，则获取现有的缓存数据，将过期的对象删除
 * 3、get对象时判断存放时间，过去删除并返回null
 * 4、add时需要存放添加时间戳
 */
public class LRUCache<T> {
    private ConcurrentHashMap<String, T> cache = new ConcurrentHashMap<>(50);
    private int size;//缓存容量
    private long expire_time;//过期时间
    private ExecutorService checkTimePool;

    public LRUCache() {

    }

    public void execute(){
        checkTimePool.execute(() ->{
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
