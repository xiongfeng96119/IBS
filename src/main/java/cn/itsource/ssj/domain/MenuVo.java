package cn.itsource.ssj.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_menu")
public class MenuVo extends BaseDomain{

    private String name;

    private String url;

    private String icon;

    private Long parent_id;

    private String parentName;

    @Override
    public String toString() {
        return "MenuVo{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", parent_id=" + parent_id +
                ", parentName='" + parentName + '\'' +
                '}';
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
