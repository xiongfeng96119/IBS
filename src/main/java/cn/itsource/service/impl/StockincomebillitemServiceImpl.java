package cn.itsource.service.impl;

import java.math.BigDecimal;
import cn.itsource.domain.Stockincomebillitem;
import cn.itsource.repository.IStockincomebillitemRepository;
import cn.itsource.service.IStockincomebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Stockincomebillitem)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:58
 */
@Service
@Transactional(readOnly = true)
public class StockincomebillitemServiceImpl extends BaseServiceImpl<Stockincomebillitem> implements IStockincomebillitemService{
    @Autowired
    private IStockincomebillitemRepository stockincomebillitemRepository;
}