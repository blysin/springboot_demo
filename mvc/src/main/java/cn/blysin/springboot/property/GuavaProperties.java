package cn.blysin.springboot.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Blysin
 * @date 2017/7/25
 */
@Data
@Component
@ConfigurationProperties(prefix = "guava")
public class GuavaProperties {
    private Integer initNum;
    private String threadProfix;
    @Value("value注解注入属性值")
    private String type;
}
