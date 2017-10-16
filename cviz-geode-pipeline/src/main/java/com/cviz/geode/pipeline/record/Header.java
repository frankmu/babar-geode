package com.cviz.geode.pipeline.record;

import java.util.Date;

public class Header {

    private Long number;
    private String source;
    private Date creationDate;

    public Header(Long number, String source, Date creationDate) {
        this.number = number;
        this.source = source;
        this.creationDate = creationDate;
    }

    public Long getNumber() {
        return number;
    }

    public String getSource() {
        return source;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}