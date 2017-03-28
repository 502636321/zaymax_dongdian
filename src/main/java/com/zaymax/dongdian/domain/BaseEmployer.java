package com.zaymax.dongdian.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 雇主
 * Created by soy50 on 2017/3/26.
 */
@Entity
@Table(name = "BASE_EMPLOYER")
public class BaseEmployer extends BaseDomain {

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
