package com.robinwu.api.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Robin on 16/5/30.
 */

public class MingYan {
    private Integer id;
    private String author;
    private String content;
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object obj) {
        String[] key = {"id","author","content","categoryId"};
        return EqualsBuilder.reflectionEquals(this,obj,key);
    }

    @Override
    public int hashCode() {
        String[] key = {"id","author","content","categoryId"};
        return HashCodeBuilder.reflectionHashCode(this,key);
    }
}
