package cn.blysin.springboot;

import cn.blysin.springboot.property.UserProperties;
import cn.blysin.springboot.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestfullApplicationTests {
	@Autowired
	DeptService deptService;
	@Autowired
	private UserProperties userProperties;

	@Test
	public void contextLoads() {
		log.debug("------------------------>test");
		System.out.println(deptService.findAll());
		System.out.println(userProperties);
	}

}
