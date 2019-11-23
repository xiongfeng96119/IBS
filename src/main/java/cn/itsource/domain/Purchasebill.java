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
 * (采购单)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-18 09:24:56
 */
@Entity
@Table(name = "purchasebill")
public class Purchasebill extends BaseDomain {

    //采购日期【前端使用easyui的datebox选择】
    private Date vdate;
    //总金额【选择采购单明细之后通过明细的小计金额计算出来】
    private BigDecimal totalamount;
    //总数量【计算】
    private BigDecimal totalnum;
    //录入时间【录入人提交采购单的时候获取系统当前时间】
    private Date inputtime = new Date();
    //审核时间【审核人点击审核按钮是获取系统当前时间，默认null】
    private Date auditortime;
    //采购单状态  1表示待审  2表示已审核  3表示已作废
    private Integer status = 1;

    //供应商【前端使用easyui的combobox下拉框选择】
    @ManyToOne(fetch = FetchType.LAZY)  //JPA
    @JoinColumn(name = "supplier_id") //JPA
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  //Jackson
    private Supplier supplier;

    //审核人【审核人点击审核按钮时获取当前登录用户】
    @ManyToOne(fetch = FetchType.LAZY)  //JPA
    @JoinColumn(name = "auditor_id") //JPA
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  //Jackson
    private Employee auditor;

    //录入人【提交采购单的时候获取当前登录用户】
    @ManyToOne(fetch = FetchType.LAZY)  //JPA
    @JoinColumn(name = "inputuser_id") //JPA
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  //Jackson
    private Employee inputuser;

    //采购员【前端使用easyui的combobox下拉框选择】
    @ManyToOne(fetch = FetchType.LAZY)  //JPA
    @JoinColumn(name = "buyer_id") //JPA
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})  //Jackson
    private Employee buyer;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bill",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Purchasebillitem> billitems = new ArrayList<>();


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

    public List<Purchasebillitem> getBillitems() {
        return billitems;
    }

    public void setBillitems(List<Purchasebillitem> billitems) {
        this.billitems = billitems;
    }
}