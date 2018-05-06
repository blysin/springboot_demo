package cn.blysin.springboot.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:blysin.properties")
public class BlysinProperties {
    @Value("This is value annotation")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("T(java.lang.Math).random() * 100.0")
    private double randomDouble;

    @Value("#classpath:blysin.properties")
    private Resource blysinProperties;
}
