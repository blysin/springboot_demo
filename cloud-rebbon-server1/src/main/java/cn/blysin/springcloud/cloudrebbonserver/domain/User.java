package cn.blysin.springcloud.cloudrebbonserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Blysin
 * @since 2019-05-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String userUuId;
    private Integer age;
    private String address;
}
