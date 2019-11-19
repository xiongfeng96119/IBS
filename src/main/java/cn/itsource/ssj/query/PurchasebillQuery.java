package cn.itsource.ssj.query;

import java.util.Date;
import cn.itsource.ssj.domain.Purchasebill;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (Purchasebill)高级查询实体类
 * @author 熊峰
 * @since 2019-11-18 10:22:57
 */
public class PurchasebillQuery extends BaseQuery<Purchasebill> {
    
    private Date vdate1;
    private Date vdate2;
    private Long supplierId;
    private Long buyerId;
    private Integer status;

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

    @Override
    public Specification createSpecification() {
        Date endDate = null;
        if (vdate2!=null){
            endDate = DateUtils.addDays(vdate2, 1);
        }
        return  Specifications.<Purchasebill>and()
                .ge(vdate1!=null,"vdate",vdate1)
                .lt(endDate!=null,"vdate",endDate)
                .eq(supplierId!=null&&supplierId>0, "supplier.id",supplierId)
                .eq(buyerId!=null&&buyerId>0, "buyer.id",buyerId)
                .eq(status!=null&&status>=0, "status",status)
                .build();
    }
    /**
     * 获取分页规则对象
     * @return
     */
    @Override
    public Pageable getPageable() {
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

}