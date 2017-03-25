package com.zaymax.dongdian.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by huiquan on 2017/3/25.
 */
@Entity
@Table(name = "BASA_COUNTRY")
public class BaseCountry extends BaseDomain {
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
