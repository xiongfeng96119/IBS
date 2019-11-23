package cn.itsource.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseQuery<T> {

    private Integer pageNo = 1;         //当前页码 从1开始
    private Integer pageSize = 10;      //每页行数
    private String order = "id";        //排序字段
    private String orderType = "DESC";  //排序方式

    /**
     * 获取高级查询条件Specification
     * 定义成抽象方法的好处：
     *  方法名称、参数列表、返回类型就固定下来了，那我们的Dao层所有的接口都可以使用这个方法了
     * @return
     */
    public abstract Specification<T> getSpecification();

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

    public Integer getJpaPageNo() {
        return pageNo - 1;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
