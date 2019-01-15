package cn.blysin.springboot.service.impl;

import cn.blysin.springboot.dao.DeptDao;
import cn.blysin.springboot.domain.Dept;
import cn.blysin.springboot.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    private final static String CACHE_NAME = "'guavaCache'";//必须要单引号

    @Override
    @Transactional
    public void insert(Dept dept) {
        deptDao.insert(dept);
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "'dept_'+#dept.getId()")
    public void update(Dept dept) {//update 需要将数据放入缓存

    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'dept:'+#id")
    public Dept get(Integer id) {//get操作时同步处理缓存数据
        return deptDao.get(id);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "'dept_'+#id")
    public void delete(Integer id) {//delete操作并清除缓存

    }

    @Override
    public List<Dept> findAll() {
        log.debug("find all debug");
        log.info("find all info");
        log.warn("find all warn");
        return deptDao.findAll();
    }


}
