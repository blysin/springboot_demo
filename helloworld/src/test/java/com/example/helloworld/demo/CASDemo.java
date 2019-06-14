package com.example.helloworld.demo;

import org.assertj.core.util.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Blysin
 * @since 2019-05-25
 */
public class CASDemo {
    private AtomicInteger ai = new AtomicInteger();
    public void test(){
        ai.get();
        System.out.println(ai.incrementAndGet());;
    }

    public void test2(){
        Collections.synchronizedList(new ArrayList<String>());
        Vector vector = new Vector();

        List list = Lists.newArrayList();
        //list.con

        vector.get(0);

        new CopyOnWriteArrayList<>().get(1);
        new CopyOnWriteArraySet<>().add(vector);
        new CopyOnWriteArraySet<>();

        new HashSet<>();
        new HashMap<>().put(vector, vector);
        new HashMap<>().get(vector);

        new ConcurrentHashMap<>().put(vector,vector);
        new ConcurrentHashMap<>().get(vector);

        new ConcurrentLinkedQueue<>();

    }
}
