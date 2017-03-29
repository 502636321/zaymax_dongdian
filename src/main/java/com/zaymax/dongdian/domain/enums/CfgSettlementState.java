package com.zaymax.dongdian.domain.enums;

/**
 * Created by huiquan on 2017/3/29.
 */
public enum CfgSettlementState {
    Settled, //已结算
    By; //截至

    @Override
    public String toString() {
        return getClass().getSimpleName() + "." + name();
    }
}
