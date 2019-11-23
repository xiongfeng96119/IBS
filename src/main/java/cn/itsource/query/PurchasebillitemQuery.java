package cn.itsource.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itsource.domain.Purchasebillitem;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * (Purchasebillitem)Query实体类-高级查询
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:58
 */
public class PurchasebillitemQuery extends BaseQuery<Purchasebillitem> {

    private Date vdate1;
    private Date vdate2;
    private Long supplierId;
    private Long buyerId;
    private Long producttypeId;
    private Long productId;
    private Integer status;
    private String groupField = "o.bill.supplier.name";         //分组字段
    
    //若要添加其他高级查询条件，请在这里添加字段以及setget方法
    
    /**
     * 获取高级查询规则对象
     * @return
     */
    public Specification<Purchasebillitem> getSpecification(){
        Date endDate = null;
        if(vdate2 != null){
            endDate = DateUtils.addDays(vdate2, 1);
        }
//        return Specifications.<Purchasebillitem>and()
//                .eq(supplierId!=null&&supplierId>0, "bill.supplier.id", supplierId)
//                .eq(buyerId!=null&&buyerId>0, "bill.buyer.id", buyerId)
//                .eq(status!=null&&status>0, "bill.status", status)
//                .ge(vdate1!=null, "bill.vdate", vdate1)
//                .lt(endDate!=null, "bill.vdate", endDate)
//                .eq(productId!=null&&productId>0,"product.id", productId)
//                .eq(producttypeId!=null&&producttypeId>0,"product.types.id", producttypeId)
//                //若要添加其他高级查询条件，请在这里添加其他拼接条件的方法
//                .build();
//        return new Specification<Purchasebillitem>(){
//            @Override
//            public Predicate toPredicate(Root<Purchasebillitem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                if(supplierId!=null&&supplierId>0){
//                    return criteriaBuilder.equal(root.get("bill.supplier.id"), supplierId);
//                }
//                return null;
//            }
//        };
        return null;
    }

    //成员变量 有顺序
    private List params = new ArrayList();
    /**
     * 拼接高级查询的JPQL语句
     * @return
     */
    public String getJpql(){
        StringBuilder jpql = new StringBuilder();
        if(supplierId!=null&&supplierId>0){
            jpql.append(" and o.bill.supplier.id = ?");
            params.add(supplierId);
        }
        if(buyerId!=null&&buyerId>0){
            jpql.append(" and o.bill.buyer.id = ?");
            params.add(buyerId);
        }
        if(status!=null&&status>0){
            jpql.append(" and o.bill.status = ?");
            params.add(status);
        }
        if(vdate1!=null){
            jpql.append(" and o.bill.vdate >= ?");
            params.add(vdate1);
        }
        if(vdate2 != null){
            Date endDate = DateUtils.addDays(vdate2, 1);
            jpql.append(" and o.bill.vdate < ?");
            params.add(endDate);
        }
        if(productId!=null&&productId>0){
            jpql.append(" and o.product.id = ?");
            params.add(productId);
        }
        if(producttypeId!=null&&producttypeId>0){
            jpql.append(" and o.product.types.id = ?");
            params.add(producttypeId);
        }
        return jpql.toString().replaceFirst("and", "where");
    }

//    /**
//     * 获取MySQL中的分组字段
//     * @return
//     */
    /*public String getGroup(){
        String group = null;
        switch (groupField){
            case -1:
            case 1 : group = "o.bill.supplier.name";break;
            case 2 : group = "o.bill.buyer.username";break;
            case 3 : group = "o.product.types.name";break;
            case 4 : group = "o.product.name";break;
            case 5 : group = "o.bill.status";break;
            case 6 : group = "date_format(o.bill.vdate,'%Y年%m月')";break;
        }
        return group;
    }*/

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "PurchasebillitemQuery{" +
                "vdate1=" + vdate1 +
                ", vdate2=" + vdate2 +
                ", supplierId=" + supplierId +
                ", buyerId=" + buyerId +
                ", producttypeId=" + producttypeId +
                ", productId=" + productId +
                ", status=" + status +
                '}';
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

    public Long getProducttypeId() {
        return producttypeId;
    }

    public void setProducttypeId(Long producttypeId) {
        this.producttypeId = producttypeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}