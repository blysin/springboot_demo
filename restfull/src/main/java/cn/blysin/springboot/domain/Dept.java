package cn.blysin.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Blysin
 * @date 2017/7/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept {
    private Integer id;
    private Integer num;
    private Integer pid;
    private String simplename;
    private String fullname;
    private String tips;
    private Integer version;
}
