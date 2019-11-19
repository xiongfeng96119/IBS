package cn.itsource.ssj.query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseQuery<T> {
    private int pageNo = 1;
    private int pageSize = 10;
    private String orderType = "DESC";
    private String order = "id";

    //拿到查询的条件对象(由子类实现)
    public abstract Specification createSpecification();
    /**
     * 获取分页规则对象
     * @return
     */
    public abstract Pageable getPageable();
    /**
     * 获取排序规则对象
     * @return
     */
    public abstract Sort getSort();
    /**
     * 用户前台从第一页开始，后台对应的是0
     * @return
     */
    public int getJpaPageNo() {
        return pageNo - 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


}
