package com.zaymax.dongdian.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商业保险
 * Created by huiquan on 2017/3/28.
 */
@Entity
@Table(name = "SYS_COMMERCIAL_INSURANCE")
public class SysCommercialInsurance extends BaseDomain {
    /**
     * 保费
     */
    @Column(name = "PREMIUM")
    private Double premium;

    /**
     * 保额
     */
    @Column(name = "PAID")
    private Double paid;

    /**
     * 保险期限（开始）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "START_PERIOD")
    private Date startPeriod;

    /**
     * 保险期限（结束）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_PERIOD")
    private Date endPeriod;

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }
}
