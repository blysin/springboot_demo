package cn.blysin.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
@SpringBootApplication
public class FreemarkerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreemarkerApplication.class, args);
	}
}
