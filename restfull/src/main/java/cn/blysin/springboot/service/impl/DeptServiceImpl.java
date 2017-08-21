package cn.blysin.springboot.service.impl;

import cn.blysin.springboot.dao.DeptDao;
import cn.blysin.springboot.domain.Dept;
import cn.blysin.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Blysin
 * @date 2017/7/25
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public Dept get(Integer id) {
        return deptDao.get(id);
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }
}
