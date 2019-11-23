package cn.itsource.query;

import java.math.BigDecimal;
import java.util.Date;
import cn.itsource.domain.Purchasebill;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (Purchasebill)Query实体类-高级查询
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:56
 */
public class PurchasebillQuery extends BaseQuery<Purchasebill> {
    
    private Date vdate1;
    private Date vdate2;
    private Long supplierId;
    private Long buyerId;
    private Integer status;

    //若要添加其他高级查询条件，请在这里添加字段以及setget方法
    
    /**
     * 获取高级查询规则对象
     * @return
     */
    public Specification<Purchasebill> getSpecification(){
        Date endDate = null;
        if(vdate2 != null){
            endDate = DateUtils.addDays(vdate2, 1);
        }
        return Specifications.<Purchasebill>and()
                .eq(supplierId!=null&&supplierId>0, "supplier.id", supplierId)
                .eq(buyerId!=null&&buyerId>0, "buyer.id", buyerId)
                .eq(status!=null&&status>0, "status", status)
                .ge(vdate1!=null, "vdate", vdate1)
                .lt(endDate!=null, "vdate", endDate)
                //若要添加其他高级查询条件，请在这里添加其他拼接条件的方法
                .build();
    }

    /**
     * 获取分页规则对象
     * @return
     */
    public Pageable getPageable(){
        return new PageRequest(getJpaPageNo(), getPageSize(), getSort());
    }

    /**
     * 获取排序规则对象
     * @return
     */
    public Sort getSort(){
        if(StringUtils.isNotBlank(getOrder())){
            this.setOrder("id");
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if("desc".equalsIgnoreCase(getOrderType())){
            direction = Sort.Direction.DESC;
        }
        return new Sort(new Sort.Order(direction, getOrder()));
    }

    public Date getVdate1() {
        return vdate1;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate1(Date vdate1) {
        this.vdate1 = vdate1;
    }
    public Date getVdate2() {
        return vdate2;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate2(Date vdate2) {
        this.vdate2 = vdate2;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}