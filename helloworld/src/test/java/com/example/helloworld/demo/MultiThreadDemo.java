package com.example.helloworld.demo;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现多线程同时操作一个资源，一个生产，一个消费，
 * 执行5轮
 * <p>
 * <p>
 * 设计思想：
 * 1、要有资源类，资源内要有资源和操作
 * 2、判断资源状态，操作（生产或消费），通知
 * 3、防止虚假唤醒（必须用while来阻塞，不能用if）
 * <p>
 * 4、铁三角：
 * synchronized：synchronized，wait，notify
 * ReentrantLock：lock,await,singal
 *
 * @author Blysin
 * @since 2019-05-25
 */
public class MultiThreadDemo {
    private class SyncResource {
        private final Object lock = new Object();
        private volatile int num = 0;

        //生产
        public void produce() {
            try {
                synchronized (lock) {
                    //产品不为空就不生产
                    while (num != 0) {
                        lock.wait();//等待
                    }

                    num++;

                    System.out.println(Thread.currentThread().getName() + " ---> " + num);

                    lock.notify();//唤醒其他线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //消费
        public void custom() {
            try {
                synchronized (lock) {
                    //产品为空就不消费
                    while (num == 0) {
                        lock.wait();//等待
                    }

                    num--;

                    System.out.println(Thread.currentThread().getName() + " ---> " + num);

                    lock.notify();//唤醒其他线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class LockResource {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        private int num = 0;

        //生产
        public void produce() {
            try {
                lock.lock();
                //num != 0生产一个就消费一个
                while (num != 0) {
                    condition.await();//等待
                }

                num++;

                System.out.println(Thread.currentThread().getName() + " ---> " + num);

                condition.signal();//唤醒其他线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //消费
        public void custom() {
            try {
                lock.lock();
                //num != 0生产一个就消费一个
                while (num == 0) {
                    condition.await();//等待
                }

                num--;

                System.out.println(Thread.currentThread().getName() + " ---> " + num);

                condition.signal();//唤醒其他线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    @Test
    public void test() {
        SyncResource resource = new SyncResource();
        //LockResource resource = new LockResource();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.produce();
            }
        }, "生产者").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.custom();
            }
        }, "消费者").start();


        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }

}
