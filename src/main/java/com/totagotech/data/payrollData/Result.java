package com.totagotech.data.payrollData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("tran_dt")
    @Expose
    private String tranDt;
    @SerializedName("value_dt")
    @Expose
    private String valueDt;
    @SerializedName("origin_bus_unit")
    @Expose
    private String originBusUnit;
    @SerializedName("acct_bus_unit")
    @Expose
    private String acctBusUnit;
    @SerializedName("svce_channel")
    @Expose
    private String svceChannel;
    @SerializedName("acct_no")
    @Expose
    private String acctNo;
    @SerializedName("txn_code")
    @Expose
    private String txnCode;
    @SerializedName("dr_cr")
    @Expose
    private String drCr;
    @SerializedName("tran_ref")
    @Expose
    private String tranRef;
    @SerializedName("narrative")
    @Expose
    private String narrative;
    @SerializedName("txn_ccy")
    @Expose
    private String txnCcy;
    @SerializedName("acct_ccy")
    @Expose
    private String acctCcy;
    @SerializedName("acct_amt")
    @Expose
    private Double acctAmt;
    @SerializedName("chq_no")
    @Expose
    private Integer chqNo;

    public String getTranDt() {
        return tranDt;
    }

    public void setTranDt(String tranDt) {
        this.tranDt = tranDt;
    }

    public String getValueDt() {
        return valueDt;
    }

    public void setValueDt(String valueDt) {
        this.valueDt = valueDt;
    }

    public String getOriginBusUnit() {
        return originBusUnit;
    }

    public void setOriginBusUnit(String originBusUnit) {
        this.originBusUnit = originBusUnit;
    }

    public String getAcctBusUnit() {
        return acctBusUnit;
    }

    public void setAcctBusUnit(String acctBusUnit) {
        this.acctBusUnit = acctBusUnit;
    }

    public String getSvceChannel() {
        return svceChannel;
    }

    public void setSvceChannel(String svceChannel) {
        this.svceChannel = svceChannel;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public String getDrCr() {
        return drCr;
    }

    public void setDrCr(String drCr) {
        this.drCr = drCr;
    }

    public String getTranRef() {
        return tranRef;
    }

    public void setTranRef(String tranRef) {
        this.tranRef = tranRef;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getTxnCcy() {
        return txnCcy;
    }

    public void setTxnCcy(String txnCcy) {
        this.txnCcy = txnCcy;
    }

    public String getAcctCcy() {
        return acctCcy;
    }

    public void setAcctCcy(String acctCcy) {
        this.acctCcy = acctCcy;
    }

    public Double getAcctAmt() {
        return acctAmt;
    }

    public void setAcctAmt(Double acctAmt) {
        this.acctAmt = acctAmt;
    }

    public Integer getChqNo() {
        return chqNo;
    }

    public void setChqNo(Integer chqNo) {
        this.chqNo = chqNo;
    }

}
