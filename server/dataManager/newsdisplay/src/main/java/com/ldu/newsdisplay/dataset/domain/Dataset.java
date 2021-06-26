package com.ldu.newsdisplay.dataset.domain;

import java.io.Serializable;
import java.util.Date;

public class Dataset implements Serializable {
    private Long datasetId;

    private Integer newsCount;

    private Integer fakeCount;

    private Date createDate;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public Integer getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(Integer newsCount) {
        this.newsCount = newsCount;
    }

    public Integer getFakeCount() {
        return fakeCount;
    }

    public void setFakeCount(Integer fakeCount) {
        this.fakeCount = fakeCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}