package cn.itsource.service.impl;

import java.math.BigDecimal;
import cn.itsource.domain.Depot;
import cn.itsource.repository.IDepotRepository;
import cn.itsource.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Depot)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:54
 */
@Service
@Transactional(readOnly = true)
public class DepotServiceImpl extends BaseServiceImpl<Depot> implements IDepotService{
    @Autowired
    private IDepotRepository depotRepository;
}