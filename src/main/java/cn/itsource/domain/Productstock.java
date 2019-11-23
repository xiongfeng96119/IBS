package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * (Productstock)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:56
 */
@Entity
@Table(name = "productstock")
public class Productstock extends BaseDomain {
    
        
    private BigDecimal num;
        
    private BigDecimal amount;
        
    private BigDecimal price;
        
    private Date incomedate = new Date();
        
    private Boolean warning;
        
    private BigDecimal topnum;
        
    private BigDecimal bottomnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depot_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Depot depot;

    
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
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Date getIncomedate() {
        return incomedate;
    }

    public void setIncomedate(Date incomedate) {
        this.incomedate = incomedate;
    }
    
    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }
    
    public BigDecimal getTopnum() {
        return topnum;
    }

    public void setTopnum(BigDecimal topnum) {
        this.topnum = topnum;
    }
    
    public BigDecimal getBottomnum() {
        return bottomnum;
    }

    public void setBottomnum(BigDecimal bottomnum) {
        this.bottomnum = bottomnum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
}