package cn.itsource.ssj.domain;

import javax.persistence.*;
/**
 * (Systemdictionarytype)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:32
 */
@Entity
@Table(name="systemdictionarytype")
public class Systemdictionarytype extends BaseDomain{
 
    private String sn;
    private String name;

    public static final String PRODUCT_UNIT = "productUnit";

    public static final String PRODUCT_BRAND = "productBrand";

    public Systemdictionarytype(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Systemdictionarytype() {
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}