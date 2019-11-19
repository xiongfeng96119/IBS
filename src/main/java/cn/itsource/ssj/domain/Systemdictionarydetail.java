package cn.itsource.ssj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
/**
 * (Systemdictionarydetail)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:31
 */
@Entity
@Table(name="systemdictionarydetail")
public class Systemdictionarydetail extends BaseDomain{
 
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Systemdictionarytype types;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Systemdictionarytype getTypes() {
        return types;
    }

    public void setTypes(Systemdictionarytype types) {
        this.types = types;
    }
}