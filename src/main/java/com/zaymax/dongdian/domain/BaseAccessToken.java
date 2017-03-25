package com.zaymax.dongdian.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信API access_token(默认一个小时刷新一次)
 * Created by huiquan on 2017/3/7.
 */
@Entity
@Table(name = "BASE_ACCESS_TOKEN")
public class BaseAccessToken extends BaseDomain {

    @JsonProperty(value = "access_token")
    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    @Column(name = "EXPIRES_IN")
    private Integer expiresIn;

    public BaseAccessToken() {
    }

    public BaseAccessToken(String accessToken, Integer expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
