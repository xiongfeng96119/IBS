package cn.itsource.service.impl;

import cn.itsource.domain.Dept;
import cn.itsource.repository.IDeptRepository;
import cn.itsource.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Dept)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-08 15:43:05
 */
@Service
@Transactional(readOnly = true)
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements IDeptService{
    @Autowired
    private IDeptRepository deptRepository;
}