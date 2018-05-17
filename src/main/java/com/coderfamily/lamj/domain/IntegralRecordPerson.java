package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.IntegralRecordEntity;
import com.github.pagehelper.PageInfo;

/**
 * @author ZhangDL
 * @date 2018/3/19 09:11
 */
public class IntegralRecordPerson {

    private PageInfo<IntegralRecordEntity> mPageInfo;

    private int total;

    public PageInfo<IntegralRecordEntity> getmPageInfo() {
        return mPageInfo;
    }

    public void setmPageInfo(PageInfo<IntegralRecordEntity> mPageInfo) {
        this.mPageInfo = mPageInfo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
