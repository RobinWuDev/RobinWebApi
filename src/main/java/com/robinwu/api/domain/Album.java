package com.robinwu.api.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Robin on 16/5/30.
 */

public class Album {
    private Integer id;
    private String name;
    private String year;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        String[] key = {"id","name","year"};
        return EqualsBuilder.reflectionEquals(this,obj,key);
    }

    @Override
    public int hashCode() {
        String[] key = {"id","name","year"};
        return HashCodeBuilder.reflectionHashCode(this,key);
    }
}
