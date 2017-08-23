package cn.blysin.springboot.service.impl;

import cn.blysin.springboot.dao.DeptDao;
import cn.blysin.springboot.domain.Dept;
import cn.blysin.springboot.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Blysin
 * @date 2017/7/25
 */
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public Dept get(Integer id) {
        return deptDao.get(id);
    }

    @Override
    public List<Dept> findAll() {
        log.debug("find all debug");
        log.info("find all info");
        log.warn("find all warn");
        return deptDao.findAll();
    }

    @Override
    @Transactional
    public void insert(Dept dept) {
        deptDao.insert(dept);
//        throw new RuntimeException("测试");
    }
}
