package com.example.helloworld;

import com.example.helloworld.Service.HelloSerivce;
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

	}

}
