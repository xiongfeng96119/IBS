package cn.itsource.service.impl;

import cn.itsource.domain.Systemdictionarydetail;
import cn.itsource.repository.ISystemdictionarydetailRepository;
import cn.itsource.service.ISystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Systemdictionarydetail)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:09
 */
@Service
@Transactional(readOnly = true)
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail> implements ISystemdictionarydetailService{
    @Autowired
    private ISystemdictionarydetailRepository systemdictionarydetailRepository;

    @Override
    public List<Systemdictionarydetail> findDictionaryDetails(String sn) {
        String jpql = "select sdd from Systemdictionarydetail sdd where sdd.types.sn=?1";
        List<Systemdictionarydetail> list = systemdictionarydetailRepository.findByJpql(jpql, sn);
        if("productUnit".equals(sn)){
            list.add(0, new Systemdictionarydetail(-1L,"请选择计量单位"));
        }else if("productBrand".equals(sn)){
            list.add(0, new Systemdictionarydetail(-1L,"请选择商品品牌"));
        }
        return list;
    }
}