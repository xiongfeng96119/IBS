package cn.itsource.ssj.domain;

import cn.itsource.ssj.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Menu)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-12 09:37:11
 */
@Entity
@Table(name="menu")
public class Menu extends BaseDomain {
 
    private String name;
    private String url;
    private String icon;
    //父级菜单
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Menu parent;
    //子级菜单
    @Transient
    private List<Menu> children = new ArrayList<>();
    
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
    
    public String getIconCls() {
        return icon;
    }
    public String getIcon() {
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
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
}