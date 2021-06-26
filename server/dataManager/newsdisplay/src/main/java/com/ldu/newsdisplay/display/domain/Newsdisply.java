package com.ldu.newsdisplay.display.domain;

import java.io.Serializable;

public class Newsdisply implements Serializable {
    private Long id;

    private String title;

    private Boolean sign;

    private Long datasetId;

    private String text;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}