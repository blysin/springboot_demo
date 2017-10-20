package cn.blysin.springboot.property;

import cn.blysin.springboot.domain.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    private List<Student> students = new ArrayList<>();
}
