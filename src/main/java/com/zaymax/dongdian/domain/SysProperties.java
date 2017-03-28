package com.zaymax.dongdian.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统配置
 * Created by huiquan on 2017/3/3.
 */
@Entity
@Table(name = "SYS_PROPERTIES")
public class SysProperties extends BaseDomain {

    /**
     * 微信appID
     */
    @Column(name = "WX_APP_ID")
    private String wxAppId;

    /**
     * 微信appSecret
     */
    @Column(name = "WX_APP_SECRET")
    private String wxAppSecret;

    /**
     * 微信是否自动刷新ACCESS_TOKEN
     */
    @Column(name = "WX_AUTO_REFRESH_ACCESS_TOKEN")
    private Boolean wxAutoRefreshAccessToken = Boolean.TRUE;

    /**
     * 微信自动刷新ACCESS_TOKEN的间隔
     */
    @Column(name = "WX_AUTO_REFRESH_ACCESS_TOKEN_INTERVAL")
    private Integer wxAutoRefreshAccessTokenInterval = 3600;

    /**
     * 系统标题（左上角大字）
     */
    @Column(name = "SYSTEM_TITLE")
    private String systemTitle;

    /**
     * 自动根据名称创建外派国别
     * @return
     */
    @Column(name = "AUTO_CREATE_COUNTRY")
    private Boolean autoCreateCountry = Boolean.FALSE;

    /**
     * 自动根据名称创建雇主
     */
    @Column(name = "AUTO_CREATE_EMPLOYER")
    private Boolean autoCreateEmployer = Boolean.FALSE;

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getWxAppSecret() {
        return wxAppSecret;
    }

    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }

    public String getSystemTitle() {
        return systemTitle;
    }

    public void setSystemTitle(String systemTitle) {
        this.systemTitle = systemTitle;
    }

    public Boolean getWxAutoRefreshAccessToken() {
        return wxAutoRefreshAccessToken;
    }

    public void setWxAutoRefreshAccessToken(Boolean wxAutoRefreshAccessToken) {
        this.wxAutoRefreshAccessToken = wxAutoRefreshAccessToken;
    }

    public Integer getWxAutoRefreshAccessTokenInterval() {
        return wxAutoRefreshAccessTokenInterval;
    }

    public void setWxAutoRefreshAccessTokenInterval(Integer wxAutoRefreshAccessTokenInterval) {
        this.wxAutoRefreshAccessTokenInterval = wxAutoRefreshAccessTokenInterval;
    }

    public Boolean getAutoCreateCountry() {
        return autoCreateCountry;
    }

    public void setAutoCreateCountry(Boolean autoCreateCountry) {
        this.autoCreateCountry = autoCreateCountry;
    }

    public Boolean getAutoCreateEmployer() {
        return autoCreateEmployer;
    }

    public void setAutoCreateEmployer(Boolean autoCreateEmployer) {
        this.autoCreateEmployer = autoCreateEmployer;
    }
}
