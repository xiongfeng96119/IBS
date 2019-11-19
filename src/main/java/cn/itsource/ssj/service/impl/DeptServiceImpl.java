package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.domain.Dept;
import cn.itsource.ssj.repository.IDeptRepository;
import cn.itsource.ssj.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Dept)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-08 19:29:00
 */
 @Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements IDeptService{
    @Autowired
    private IDeptRepository deptRepository;
}