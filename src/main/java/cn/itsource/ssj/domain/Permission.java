package cn.itsource.ssj.domain;

import javax.persistence.*;
/**
 * (Permission)domain实体类
 *
 * @author 熊峰
 * @since 2019-11-12 09:44:40
 */
@Entity
@Table(name="permission")
public class Permission extends BaseDomain{
 
    private String name;
    private String url;
    private String descs;
    private String sn;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    
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
    
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
    
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", descs='" + descs + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}