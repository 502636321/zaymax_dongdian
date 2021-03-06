package com.zaymax.dongdian.domain;

import com.zaymax.dongdian.domain.enums.CfgGender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 外派人员
 * Created by soy50 on 2017/3/26.
 */
@Entity
@Table(name = "SYS_EXPATRIATE")
public class SysExpatriate extends BaseDomain {

    /**
     * 编号
     */
    @Column(name = "NUMBER")
    private String number;

    /**
     * 名字
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private CfgGender gender;

    /**
     * 身份证号码
     */
    @Column(name = "CARD_NO")
    private String cardNO;

    /**
     * 护照号码
     */
    @Column(name = "PASSPORT_NO")
    private String passportNO;

    /**
     * 联系地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 联系电话
     */
    @Column(name = "CONTACT_MOBILE")
    private String contactMobile;

    /**
     * 派往国别
     */
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private BaseCountry country;


    /**
     * 雇主名称
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYER_ID")
    private BaseEmployer employer;

    /**
     * 外派时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "EXPATRIATE_DATE")
    private Date expatriateDate;

    /**
     * 合同期限(单位为:月)
     */
    @Column(name = "CONTRACT_PERIOD")
    private Integer contractPeriod;

    /**
     * 社会保险
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "SOCIAL_INSURANCE_ID")
    private SysSocialInsurance socialInsurance;

    /**
     * 商业保险
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "COMMERCIAL_INSURANCE_ID")
    private SysCommercialInsurance commercialInsurance;

    /**
     * 管理费
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "MANAGE_COST_ID")
    private SysManageCost manageCost;

    /**
     * 服务费
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "SERVICE_COST_ID")
    private SysServiceCost serviceCost;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "WAGES_ID")
    private SysWages wages;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CfgGender getGender() {
        return gender;
    }

    public void setGender(CfgGender gender) {
        this.gender = gender;
    }

    public String getCardNO() {
        return cardNO;
    }

    public void setCardNO(String cardNO) {
        this.cardNO = cardNO;
    }

    public String getPassportNO() {
        return passportNO;
    }

    public void setPassportNO(String passportNO) {
        this.passportNO = passportNO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public BaseCountry getCountry() {
        return country;
    }

    public void setCountry(BaseCountry country) {
        this.country = country;
    }

    public BaseEmployer getEmployer() {
        return employer;
    }

    public void setEmployer(BaseEmployer employer) {
        this.employer = employer;
    }

    public Date getExpatriateDate() {
        return expatriateDate;
    }

    public void setExpatriateDate(Date expatriateDate) {
        this.expatriateDate = expatriateDate;
    }

    public Integer getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Integer contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public SysSocialInsurance getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(SysSocialInsurance socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public SysCommercialInsurance getCommercialInsurance() {
        return commercialInsurance;
    }

    public void setCommercialInsurance(SysCommercialInsurance commercialInsurance) {
        this.commercialInsurance = commercialInsurance;
    }

    public SysManageCost getManageCost() {
        return manageCost;
    }

    public void setManageCost(SysManageCost manageCost) {
        this.manageCost = manageCost;
    }

    public SysServiceCost getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(SysServiceCost serviceCost) {
        this.serviceCost = serviceCost;
    }

    public SysWages getWages() {
        return wages;
    }

    public void setWages(SysWages wages) {
        this.wages = wages;
    }
}
