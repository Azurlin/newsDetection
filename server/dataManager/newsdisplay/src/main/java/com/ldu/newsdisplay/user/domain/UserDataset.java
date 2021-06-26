package com.ldu.newsdisplay.user.domain;

import java.io.Serializable;

public class UserDataset implements Serializable {
    private Long id;

    private Long userId;

    private Long datasetId;

    private Byte active;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }
}