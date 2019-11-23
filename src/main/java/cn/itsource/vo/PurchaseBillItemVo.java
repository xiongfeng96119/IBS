package cn.itsource.vo;

import cn.itsource.domain.Purchasebillitem;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class PurchaseBillItemVo {

    private Long id;
    private String supplier;//供应商
    private String buyer; //采购员名称
    private String product; //产品名称
    private String productType; //产品分类
    private Date vdate; //交易时间
    private BigDecimal num; //采购数量
    private BigDecimal price; //价格
    private BigDecimal amount; //小计 = 价格*数量
    private Integer status;//采购单状态 1-待审 2-已审 3-作废
    private String groupField = supplier; //分组字段

    public PurchaseBillItemVo() {
    }

    public PurchaseBillItemVo(Purchasebillitem item, String groupField) {
        this.id = item.getId();
        this.supplier = item.getBill().getSupplier().getName();
        this.buyer = item.getBill().getBuyer().getUsername();
        this.product = item.getProduct().getName();
        this.productType = item.getProduct().getTypes().getName();
        this.vdate = item.getBill().getVdate();
        this.price = item.getPrice();
        this.num = item.getNum();
        this.amount = item.getAmount();
        this.status = item.getBill().getStatus();
        switch (groupField){
            case "o.bill.supplier.name" : this.groupField = supplier;break;
            case "o.bill.buyer.username" : this.groupField = buyer;break;
            case "o.product.types.name" : this.groupField = productType;break;
            case "o.product.name" : this.groupField = product;break;
            case "o.bill.status" : this.groupField = status == 1 ? "待审" : (status == 2 ? "已审" : "作废");break;
            case "date_format(o.bill.vdate,'%Y年%m月')" :
                Calendar c = DateUtils.toCalendar(vdate);
                this.groupField = c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月";
                break;
            default: this.groupField = supplier;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
