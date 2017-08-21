package cn.blysin.springboot.service;

import cn.blysin.springboot.domain.Dept;

import java.util.List;

/**
 * @author Blysin
 * @date 2017/7/25
 */
public interface DeptService {
    Dept get(Integer id);

    List<Dept> findAll();
}
