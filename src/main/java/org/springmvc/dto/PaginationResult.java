package org.springmvc.dto;

import java.util.List;

/**
 * @Description: 返回前端数据的分页数据结构
 * @Author: Shalldid
 * @Date: Created in 12:08 2018-04-28
 * @Return:
 **/
public class PaginationResult<T> {

    private boolean lastPage;
    private int pageSize;
    private int pageNumber;
    private boolean firstPage;
    private List<T> list;
    private int totalRow;
    private int totalPage;
    //返回Base64编码
    private String base64Url;

    public String getBase64Url() {
        return base64Url;
    }

    public void setBase64Url(String base64Url) {
        this.base64Url = base64Url;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public PaginationResult(boolean lastPage, int pageSize, int pageNumber, boolean firstPage, List<T> list, int totalRow, int totalPage) {
        this.lastPage = lastPage;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.firstPage = firstPage;
        this.list = list;
        this.totalRow = totalRow;
        this.totalPage = totalPage;
    }

    public PaginationResult(boolean lastPage, int pageSize, int pageNumber, boolean firstPage, List<T> list, int totalRow, int totalPage, String base64Url) {
        this.lastPage = lastPage;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.firstPage = firstPage;
        this.list = list;
        this.totalRow = totalRow;
        this.totalPage = totalPage;
        this.base64Url = base64Url;
    }

    public PaginationResult() {
    }

    @Override
    public String toString() {
        return "PaginationResult{" +
                "lastPage=" + lastPage +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", firstPage=" + firstPage +
                ", list=" + list +
                ", totalRow=" + totalRow +
                ", totalPage=" + totalPage +
                ", base64Url='" + base64Url + '\'' +
                '}';
    }
}
