package com.robinwu.api.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Robin on 16/5/30.
 */

public class MingYanCategory {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        String[] key = {"id","name"};
        return EqualsBuilder.reflectionEquals(this,obj,key);
    }

    @Override
    public int hashCode() {
        String[] key = {"id","name"};
        return HashCodeBuilder.reflectionHashCode(this,key);
    }
}
