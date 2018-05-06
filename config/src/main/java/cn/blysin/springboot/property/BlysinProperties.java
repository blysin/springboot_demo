package cn.blysin.springboot.property;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@PropertySource("classpath:blysin.properties")
public class BlysinProperties {
    @Value("This is value annotation")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double currnetTime;

//    @Value("#{demoService.another}")//import property from another bean
//    private String fromAnother;

    @Value("classpath:blysin.properties")
    private Resource blysinProperties;//注入配置文件

    @Value("http://www.baidu.com")
    private Resource baiduUrl;

    @Value("${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

    public void outPut() throws IOException {
        System.out.println(normal);
        System.out.println(osName);
        System.out.println(currnetTime);
        System.out.println(IOUtils.toString(baiduUrl.getInputStream()));
        System.out.println(bookName);
        System.out.println(environment.getProperty("book.author"));
        System.out.println(IOUtils.toString(blysinProperties.getInputStream()));
    }


}
