package cn.blysin.springboot;

import cn.blysin.springboot.property.GuavaProperties;
import cn.blysin.springboot.property.UserProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigApplicationTests {
	@Autowired
	private UserProperties userProperties;
	@Autowired
	private GuavaProperties guavaProperties;

	@Test
	public void contextLoads() {
		System.out.println(userProperties.toString());
		System.out.println(guavaProperties.toString());
	}

}
