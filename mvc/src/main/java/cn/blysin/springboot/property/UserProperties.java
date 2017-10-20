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
@ConfigurationProperties(prefix = "blysin")
public class UserProperties {
    private Boolean debugger;
    private String name;
}
