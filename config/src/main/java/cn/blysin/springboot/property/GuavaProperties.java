package cn.blysin.springboot.property;

import lombok.Data;
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
    private String type;
}
