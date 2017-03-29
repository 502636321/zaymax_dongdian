package com.zaymax.dongdian.domain;

import com.zaymax.dongdian.domain.enums.CfgSettlementState;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 社会保险
 * Created by huiquan on 2017/3/28.
 */

@Entity
@Table(name = "SYS_SOCIAL_INSURANCE")
public class SysSocialInsurance extends BaseDomain {
    /**
     * 参保时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "INSURANCE_DATE")
    private Date insuranceDate;

    /**
     * 个人编码
     */
    @Column(name = "PERSONAL_CODE")
    private String personalCode;

    /**
     * 基数
     */
    @Column(name = "RADICES")
    private Double radices;

    /**
     * 公司基数
     */
    @Column(name = "COMPANY_RADICES")
    private Double companyRadices;

    /**
     * 个人基数
     */
    @Column(name = "PERSONAL_RADICES")
    private Double personalRadices;

    /**
     * 结算状态
     */
    @Column(name = "SETTLEMENT_STATE")
    private CfgSettlementState settlementState;

    /**
     * 截至日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "SETTLEMENT_DATE")
    private Date settlementDate;

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public Double getRadices() {
        return radices;
    }

    public void setRadices(Double radices) {
        this.radices = radices;
    }

    public Double getCompanyRadices() {
        return companyRadices;
    }

    public void setCompanyRadices(Double companyRadices) {
        this.companyRadices = companyRadices;
    }

    public Double getPersonalRadices() {
        return personalRadices;
    }

    public void setPersonalRadices(Double personalRadices) {
        this.personalRadices = personalRadices;
    }

    public CfgSettlementState getSettlementState() {
        return settlementState;
    }

    public void setSettlementState(CfgSettlementState settlementState) {
        this.settlementState = settlementState;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}
