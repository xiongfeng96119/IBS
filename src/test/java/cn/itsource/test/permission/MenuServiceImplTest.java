package cn.itsource.test.permission;

import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.query.MenuVoQuery;
import cn.itsource.service.IMenuService;
import cn.itsource.service.IMenuVoService;
import cn.itsource.test.BaseTest;
import cn.itsource.utils.MyPage;
import cn.itsource.vo.MenuVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImplTest extends BaseTest {

    @Autowired
    private IMenuVoService menuVoService;

    @Test
    public void test() throws Exception{

        MyPage<MenuVo> page = menuVoService.findByPage(new MenuVoQuery());
        page.getRows().forEach(menuVo -> System.out.println(menuVo));
    }

}
