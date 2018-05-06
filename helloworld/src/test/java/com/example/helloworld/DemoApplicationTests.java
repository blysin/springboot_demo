package com.example.helloworld;

import com.example.helloworld.Service.HelloSerivce;
import com.example.helloworld.demo.LRUCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private HelloSerivce helloSerivce;

	@Test
	public void contextLoads() {
		System.out.println(helloSerivce.hello());
	}

	@Test
	public void executorTest(){
		LRUCache cache = new LRUCache<>();
		cache.execute();
		cache.execute();
		cache.execute();
		cache.execute();
		cache.execute();

		LRUCache cache2 = new LRUCache<>();
		cache2.execute();
		cache2.execute();
		cache2.execute();
		cache2.execute();
		while (true){}
	}

}
