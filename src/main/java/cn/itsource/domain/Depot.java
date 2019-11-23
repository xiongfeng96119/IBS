package cn.itsource.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Depot)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:54
 */
@Entity
@Table(name = "depot")
public class Depot extends BaseDomain {
    
        
    private String name;
        
    private BigDecimal maxcapacity;
        
    private BigDecimal currentcapacity;
        
    private BigDecimal totalamount;

    public Depot() {
    }

    public Depot(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(BigDecimal maxcapacity) {
        this.maxcapacity = maxcapacity;
    }
    
    public BigDecimal getCurrentcapacity() {
        return currentcapacity;
    }

    public void setCurrentcapacity(BigDecimal currentcapacity) {
        this.currentcapacity = currentcapacity;
    }
    
    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

}