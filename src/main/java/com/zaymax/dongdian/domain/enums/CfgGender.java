package com.zaymax.dongdian.domain.enums;

/**
 * 性别
 * Created by soy50 on 2017/3/26.
 */
public enum CfgGender {
    Male,
    Female;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "." + name();
    }
}
