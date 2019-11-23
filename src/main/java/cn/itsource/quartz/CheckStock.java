package cn.itsource.quartz;

import cn.itsource.domain.Productstock;
import cn.itsource.repository.IProductstockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CheckStock {

    @Autowired
    private IProductstockRepository productstockRepository;

    public void work(){
        List<Productstock> list = productstockRepository.findAll();
        for (Productstock productstock : list) {
            if(!productstock.getWarning()){
                continue;
            }
            //当前库存数量达到最低值
            if(productstock.getNum().doubleValue() <= productstock.getBottomnum().doubleValue()){
                //发出预警
                //..........
            }
            //当前库存数量达到最高值
            if(productstock.getNum().doubleValue() >= productstock.getTopnum().doubleValue()){
                //发出预警
                //..........
            }
        }
    }

}
