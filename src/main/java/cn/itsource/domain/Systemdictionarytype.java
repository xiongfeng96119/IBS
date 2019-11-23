package cn.itsource.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Systemdictionarytype)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:10
 */
@Entity
@Table(name = "systemdictionarytype")
public class Systemdictionarytype extends BaseDomain {

        
    private String sn;
        
    private String name;

    public Systemdictionarytype() {
    }

    public Systemdictionarytype(Long id, String name) {
        this.setId(id);
        this.name = name;
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