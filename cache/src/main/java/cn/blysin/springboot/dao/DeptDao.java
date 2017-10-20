package cn.blysin.springboot.dao;

import cn.blysin.springboot.domain.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Blysin
 * @date 2017/7/25
 */
@Mapper
public interface DeptDao {
    Dept get(Integer id);
    List<Dept> findAll();
    void insert(Dept dept);
}
