package cn.blysin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}


}
