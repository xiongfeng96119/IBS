package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * (采购单明细)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:58
 */
@Entity
@Table(name = "purchasebillitem")
public class Purchasebillitem extends BaseDomain {

    
    //单价【前端输入框填写】
    private BigDecimal price;
    //数量【前端输入框填写】
    private BigDecimal num;
    //小计金额【提交到后端直接计算】
    private BigDecimal amount;
    //描述【前端输入框填写】
    private String descs;
    //采购的商品【前端用easyui的combobox下拉框选择】
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Product product;
    //采购单【直接提交到后端】
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Purchasebill bill;

    @Override
    public String toString() {
        return "Purchasebillitem{" +
                "price=" + price +
                ", num=" + num +
                ", amount=" + amount +
                ", descs='" + descs + '\'' +
                ", product=" + product.getId() +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchasebill getBill() {
        return bill;
    }

    public void setBill(Purchasebill bill) {
        this.bill = bill;
    }
}