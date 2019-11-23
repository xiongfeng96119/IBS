package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * (Producttype)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:18
 */
@Entity
@Table(name = "producttype")
public class Producttype extends BaseDomain {

        
    private String name;
        
    private String descs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Producttype parent;

    public Producttype() {
    }

    public Producttype(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Producttype getParent() {
        return parent;
    }

    public void setParent(Producttype parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
    
}