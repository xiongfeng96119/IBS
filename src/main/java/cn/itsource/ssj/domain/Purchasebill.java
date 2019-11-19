package cn.itsource.ssj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
/**
 * (Purchasebill)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-18 10:22:57
 */
@Entity
@Table(name="purchasebill")
public class Purchasebill extends BaseDomain{
 
    private Date vdate;
    private BigDecimal totalamount;
    private BigDecimal totalnum;
    private Date inputtime = new Date();
    private Date auditortime = new Date();
    private Integer status = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="auditor_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Employee auditor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inputuser_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Employee inputuser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Employee buyer;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bill",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Purchasebillitem> items = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
    
    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }
    
    public BigDecimal getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(BigDecimal totalnum) {
        this.totalnum = totalnum;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getAuditortime() {
        return auditortime;
    }

    public void setAuditortime(Date auditortime) {
        this.auditortime = auditortime;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputuser() {
        return inputuser;
    }

    public void setInputuser(Employee inputuser) {
        this.inputuser = inputuser;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public List<Purchasebillitem> getItems() {
        return items;
    }

    public void setItems(List<Purchasebillitem> items) {
        this.items = items;
    }
}