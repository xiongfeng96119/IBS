package cn.itsource.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import cn.itsource.domain.Productstock;
import cn.itsource.domain.Stockincomebill;
import cn.itsource.domain.Stockincomebillitem;
import cn.itsource.repository.IStockincomebillRepository;
import cn.itsource.service.IStockincomebillService;
import cn.itsource.shiro.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Stockincomebill)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:57
 */
@Service
@Transactional(readOnly = true)
public class StockincomebillServiceImpl extends BaseServiceImpl<Stockincomebill> implements IStockincomebillService{
    @Autowired
    private IStockincomebillRepository stockincomebillRepository;

}