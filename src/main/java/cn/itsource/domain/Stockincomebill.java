package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * (入库单)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-20 14:25:57
 */
@Entity
@Table(name = "stockincomebill")
public class Stockincomebill extends BaseDomain {

    private Date vdate;
        
    private BigDecimal totalamount;
        
    private BigDecimal totalnum;
        
    private Date inputtime = new Date();
        
    private Date auditortime;
        
    private Integer status = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id") 
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  
    private Supplier supplier;          //供应商

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "auditor_id") 
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  
    private Employee auditor;           //审核人

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "inputuser_id") 
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  
    private Employee inputuser;         //录入人

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "keeper_id") 
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  
    private Employee keeper;            //库管员

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "depot_id") 
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  
    private Depot depot;                //仓库

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bill",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Stockincomebillitem> billitems = new ArrayList<>();

    @Override
    public String toString() {
        return "Stockincomebill{" +
                "id=" + getId() +
                ", vdate=" + vdate +
                ", totalamount=" + totalamount +
                ", totalnum=" + totalnum +
                ", inputtime=" + inputtime +
                ", auditortime=" + auditortime +
                ", status=" + status +
                '}';
    }

    public List<Stockincomebillitem> getBillitems() {
        return billitems;
    }

    public void setBillitems(List<Stockincomebillitem> billitems) {
        this.billitems = billitems;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
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

    public Employee getKeeper() {
        return keeper;
    }

    public void setKeeper(Employee keeper) {
        this.keeper = keeper;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
}