package cn.itsource.ssj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import javax.persistence.*;
/**
 * (Purchasebillitem)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:58
 */
@Entity
@Table(name="purchasebillitem")
public class Purchasebillitem extends BaseDomain{
 
    private BigDecimal price;
    private BigDecimal num;
    private BigDecimal amount;
    private String descs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bill_id")
    @JsonIgnore
    private Purchasebill bill;

    @Override
    public String toString() {
        return "Purchasebillitem{" +
                "price=" + price +
                ", num=" + num +
                ", amount=" + amount +
                ", descs='" + descs + '\'' +
                ", product=" + product .getId()+
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