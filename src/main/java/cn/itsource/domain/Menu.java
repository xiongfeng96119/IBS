package cn.itsource.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Menu)实体类
 *      自连接设计
 * @author 吴昌勇
 * @since 2019-11-08 16:27:35
 */
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
public class Menu extends BaseDomain {

    private String name;
        
    private String url;
        
    private String icon;

    //父级菜单:如果当前菜单是一个子菜单，则parent不为null；如果当前菜单已经是顶级菜单了，则parent为null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Menu parent;

    /**
     * 子菜单集合：如果当前菜单是一个子菜单，则children为null；如果当前菜单是顶级菜单了，则children不为null
     */
    //@OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
    @Transient
    private List<Menu> children = new ArrayList<>();

    public Menu(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getText() {
        return name;
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
    public String getIconCls() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }
}