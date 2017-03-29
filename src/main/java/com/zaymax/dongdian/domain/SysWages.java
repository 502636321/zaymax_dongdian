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
@Table(name = "SYS_WAGES")
public class SysWages extends BaseDomain{
    /**
     * 工资发放时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    /**
     * 工资金额
     */
    @Column(name = "AMOUNT")
    private Double amount;

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
