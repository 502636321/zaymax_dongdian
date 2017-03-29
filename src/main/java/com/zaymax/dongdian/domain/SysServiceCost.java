package com.zaymax.dongdian.domain;

import com.zaymax.dongdian.domain.enums.CfgSettlementState;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huiquan on 2017/3/29.
 */
@Entity
@Table(name = "SYS_SERVICE_COST")
public class SysServiceCost extends BaseDomain{
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
