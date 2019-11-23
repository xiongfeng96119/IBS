package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * (Product)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:17
 */
@Entity
@Table(name = "product")
public class Product extends BaseDomain {

    private String name;
        
    private String color;
        
    private String pic;
        
    private String smallpic;
        
    private BigDecimal costprice;
        
    private BigDecimal saleprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Producttype types;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Systemdictionarydetail unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Systemdictionarydetail brand;

    public Product(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    
    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }
    
    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }
    
    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public Producttype getTypes() {
        return types;
    }

    public void setTypes(Producttype types) {
        this.types = types;
    }

    public Systemdictionarydetail getUnit() {
        return unit;
    }

    public void setUnit(Systemdictionarydetail unit) {
        this.unit = unit;
    }

    public Systemdictionarydetail getBrand() {
        return brand;
    }

    public void setBrand(Systemdictionarydetail brand) {
        this.brand = brand;
    }
}