package cn.itsource.service.impl;

import cn.itsource.repository.IMenuVoRepository;
import cn.itsource.service.IMenuVoService;
import cn.itsource.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VIEW(VMenu)Service层接口的实现类
 *
 * @author 吴昌勇
 * @since 2019-11-13 16:21:47
 */
@Service
@Transactional(readOnly = true)
public class MenuVoServiceImpl extends BaseServiceImpl<MenuVo> implements IMenuVoService {
    @Autowired
    private IMenuVoRepository menuVoRepository;
}