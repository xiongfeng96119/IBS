package cn.itsource.ssj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Producttype)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:29
 */
@Entity
@Table(name="producttype")
public class Producttype extends BaseDomain{
 
    private String name;
    private String descs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Producttype parent;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
    @JsonIgnore
    private List<Producttype> children = new ArrayList<>();
    
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

    public Producttype getParent() {
        return parent;
    }

    public void setParent(Producttype parent) {
        this.parent = parent;
    }

    public List<Producttype> getChildren() {
        return children;
    }

    public void setChildren(List<Producttype> children) {
        this.children = children;
    }
}