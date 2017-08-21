package cn.blysin.springboot.domain;

import lombok.Data;

/**
 * @author Blysin
 * @date 2017/7/25
 */
@Data
public class Dept {
    private Integer id;
    private Integer num;
    private Integer pid;
    private String simplename;
    private String fullname;
    private String tips;
    private Integer version;
}
