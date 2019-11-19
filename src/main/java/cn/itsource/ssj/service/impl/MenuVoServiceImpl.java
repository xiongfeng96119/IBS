package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.repository.IMenuVoRepository;
import cn.itsource.ssj.service.IMenuVoService;
import cn.itsource.ssj.domain.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (VMenu)service层实现类
 *
 * @author 熊峰
 * @since 2019-11-13 22:32:22
 */
 @Service
public class MenuVoServiceImpl extends BaseServiceImpl<MenuVo> implements IMenuVoService {
    @Autowired
    private IMenuVoRepository menuVoRepository;
}