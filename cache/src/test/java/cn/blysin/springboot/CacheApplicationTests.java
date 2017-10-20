package cn.blysin.springboot;

import cn.blysin.springboot.service.DeptService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private DeptService deptService;

	@Test
	public void redisTest() {
		System.out.println(stringRedisTemplate.keys("*"));
		System.out.println(stringRedisTemplate.opsForValue().get("order"));
//		stringRedisTemplate.opsForValue().set("order1","我是订单一号",20, TimeUnit.SECONDS);
//		get();
	}

	private void get(){
		System.out.println(stringRedisTemplate.opsForValue().get("order1"));
		try {
			Thread.sleep(3000);
			get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void guavaCacheTest(){
		System.out.println(JSON.toJSON(deptService.get(24)));
		System.out.println(JSON.toJSON(deptService.get(24)));
		System.out.println(JSON.toJSON(deptService.get(24)));
	}

}
