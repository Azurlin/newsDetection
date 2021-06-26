package com.ldu.newsdisplay.sysconfig.domain;

import java.io.Serializable;
import java.util.Date;

public class Sysconfig implements Serializable {
    private Long configId;

    private String name;

    private Long configValue;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getConfigValue() {
        return configValue;
    }

    public void setConfigValue(Long configValue) {
        this.configValue = configValue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}