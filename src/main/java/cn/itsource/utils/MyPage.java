package cn.itsource.utils;

import java.util.List;

public class MyPage<T> {

    private Integer pageNo;
    private Integer pageSize;
    private Long total;
    private List<T> rows;

    public MyPage() {
    }

    public MyPage(Integer pageNo, Integer pageSize, Long total, List<T> rows) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
