package com.aliohyn.ustora.model;

import java.util.List;

public class Pagination {
    private int showItemsFrom;
    private int showItemsTo;
    private int allItemsCount;
    private int curPageItemsCount;
    private int curPageNumber;
    private List<String> pagination;

    public int getShowItemsFrom() {
        return showItemsFrom;
    }

    public void setShowItemsFrom(int showItemsFrom) {
        this.showItemsFrom = showItemsFrom;
    }

    public int getShowItemsTo() {
        return showItemsTo;
    }

    public void setShowItemsTo(int showItemsTo) {
        this.showItemsTo = showItemsTo;
    }

    public int getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(int allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public int getCurPageItemsCount() {
        return curPageItemsCount;
    }

    public void setCurPageItemsCount(int curPageItemsCount) {
        this.curPageItemsCount = curPageItemsCount;
    }

    public int getCurPageNumber() {
        return curPageNumber;
    }

    public void setCurPageNumber(int curPageNumber) {
        this.curPageNumber = curPageNumber;
    }

    public List<String> getPagination() {
        return pagination;
    }

    public void setPagination(List<String> pagination) {
        this.pagination = pagination;
    }
}
