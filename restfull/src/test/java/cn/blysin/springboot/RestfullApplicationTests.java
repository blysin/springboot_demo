package cn.blysin.springboot;

import cn.blysin.springboot.property.UserProperties;
import cn.blysin.springboot.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfullApplicationTests {
	@Autowired
	DeptService deptService;
	@Autowired
	private UserProperties userProperties;
	@Test
	public void contextLoads() {
		System.out.println(deptService.findAll());
		System.out.println(userProperties);
	}

}
