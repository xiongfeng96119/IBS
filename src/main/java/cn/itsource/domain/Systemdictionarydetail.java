package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * (Systemdictionarydetail)实体类
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:21:09
 */
@Entity
@Table(name = "systemdictionarydetail")
public class Systemdictionarydetail extends BaseDomain {

        
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
    private Systemdictionarytype types;

    public Systemdictionarydetail() {
    }

    public Systemdictionarydetail(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

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