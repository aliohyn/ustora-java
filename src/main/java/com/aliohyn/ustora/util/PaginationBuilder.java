package com.aliohyn.ustora.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationBuilder {
    private int curPage;
    private int pageCount;
    private int numPageAroundCurrent;
    private List<String> pagination;

    public List<String> build(int curPage, int pageCount, int numPageAroundCurrent) {
        this.curPage = curPage;
        this.pageCount = pageCount;
        this.numPageAroundCurrent = numPageAroundCurrent;
        this.pagination = new ArrayList<>();


        if (pageCount > 1 && curPage <= pageCount && curPage >= 1) {
            if (curPage == 1) {
                this.firstPage();
            } else if (curPage == pageCount) {
                this.lastPage();
            } else {
                this.middlePage();
            }
        }

        return this.pagination;
    }

    private void firstPage() {
        for (int i = 1; i < pageCount; i++) {
            if (i <= numPageAroundCurrent + 1) {
                pagination.add(Integer.toString(i));
            } else {
                break;
            }
        }
        if (pageCount > numPageAroundCurrent + 2) {
            pagination.add("...");
        }

        pagination.add(Integer.toString(pageCount));
    }

    private void lastPage() {
        pagination.add("1");
        if (pageCount > numPageAroundCurrent + 2) {
            pagination.add("...");
        }
        if(pageCount <= numPageAroundCurrent + 2) {
            for (int i = 2; i <= pageCount; i++) {
                pagination.add(Integer.toString(i));
            }
        } else {
            for (int i = pageCount - numPageAroundCurrent; i <= pageCount; i++) {
                pagination.add(Integer.toString(i));
            }
        }
    }

    private void middlePage() {
        if (curPage > numPageAroundCurrent + 2) {
            pagination.add("1");
            pagination.add("...");
            for (int i = curPage - numPageAroundCurrent; i <= curPage; i++) {
                pagination.add(Integer.toString(i));
            }
        } else {
            for(int i = 1; i <= curPage; i++) {
                pagination.add(Integer.toString(i));
            }
        }

        if(pageCount - curPage <= numPageAroundCurrent + 1) {
            for(int i = curPage + 1; i < pageCount; i++) {
                pagination.add(Integer.toString(i));
            }
        } else {
            for(int i = curPage + 1; i <= curPage + numPageAroundCurrent; i++) {
                pagination.add(Integer.toString(i));
            }
            pagination.add("...");
        }

        pagination.add(Integer.toString(pageCount));
    }
}
