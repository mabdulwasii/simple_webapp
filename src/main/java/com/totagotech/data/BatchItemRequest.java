/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totagotech.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Totago_User3
 */
public class BatchItemRequest {
    
        @SerializedName("TransactionBatchItemId")
        @Expose
        private Integer transactionBatchItemId;
        @SerializedName("TransactionBatchId")
        @Expose
        private Integer transactionBatchId;
        @SerializedName("EventCode")
        @Expose
        private String eventCode;
        @SerializedName("AccountNumber")
        @Expose
        private String accountNumber;
        @SerializedName("TransactionCurrencyCode")
        @Expose
        private String transactionCurrencyCode;
        @SerializedName("TransactionAmount")
        @Expose
        private Double transactionAmount;
        @SerializedName("Narrative")
        @Expose
        private String narrative;
        @SerializedName("Reference")
        @Expose
        private String reference;
        @SerializedName("DebitCreditIndicator")
        @Expose
        private String debitCreditIndicator;
        @SerializedName("ExchangeRate")
        @Expose
        private Integer exchangeRate;
        @SerializedName("AccountCurrencyAmount")
        @Expose
        private Double accountCurrencyAmount;
        @SerializedName("AccountCurrencyCode")
        @Expose
        private String accountCurrencyCode;
        @SerializedName("ValueDate")
        @Expose
        private String valueDate;
        @SerializedName("BICCode")
        @Expose
        private String bICCode;
        @SerializedName("ChequeNumber")
        @Expose
        private Integer chequeNumber;
        @SerializedName("ItemTypeCode")
        @Expose
        private String itemTypeCode;
        @SerializedName("TrackingNumber")
        @Expose
        private Integer trackingNumber;
        @SerializedName("PayerAccountNumber")
        @Expose
        private String payerAccountNumber;
        @SerializedName("BeneficiaryId")
        @Expose
        private Integer beneficiaryId;
        @SerializedName("FundTransferTypeCode")
        @Expose
        private String fundTransferTypeCode;
        @SerializedName("PaymentMethodCode")
        @Expose
        private String paymentMethodCode;
        @SerializedName("SettlementAccounNumber")
        @Expose
        private String settlementAccounNumber;
        @SerializedName("UserId")
        @Expose
        private String userId;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;

        public Integer getTransactionBatchItemId() {
        return transactionBatchItemId;
        }

        public void setTransactionBatchItemId(Integer transactionBatchItemId) {
        this.transactionBatchItemId = transactionBatchItemId;
        }

        public Integer getTransactionBatchId() {
        return transactionBatchId;
        }

        public void setTransactionBatchId(Integer transactionBatchId) {
        this.transactionBatchId = transactionBatchId;
        }

        public String getEventCode() {
        return eventCode;
        }

        public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
        }

        public String getAccountNumber() {
        return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        }

        public String getTransactionCurrencyCode() {
        return transactionCurrencyCode;
        }

        public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        this.transactionCurrencyCode = transactionCurrencyCode;
        }

        public Double getTransactionAmount() {
        return transactionAmount;
        }

        public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
        }

        public String getNarrative() {
        return narrative;
        }

        public void setNarrative(String narrative) {
        this.narrative = narrative;
        }

        public String getReference() {
        return reference;
        }

        public void setReference(String reference) {
        this.reference = reference;
        }

        public String getDebitCreditIndicator() {
        return debitCreditIndicator;
        }

        public void setDebitCreditIndicator(String debitCreditIndicator) {
        this.debitCreditIndicator = debitCreditIndicator;
        }

        public Integer getExchangeRate() {
        return exchangeRate;
        }

        public void setExchangeRate(Integer exchangeRate) {
        this.exchangeRate = exchangeRate;
        }

        public Double getAccountCurrencyAmount() {
        return accountCurrencyAmount;
        }

        public void setAccountCurrencyAmount(Double accountCurrencyAmount) {
        this.accountCurrencyAmount = accountCurrencyAmount;
        }

        public String getAccountCurrencyCode() {
        return accountCurrencyCode;
        }

        public void setAccountCurrencyCode(String accountCurrencyCode) {
        this.accountCurrencyCode = accountCurrencyCode;
        }

        public String getValueDate() {
        return valueDate;
        }

        public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
        }

        public String getBICCode() {
        return bICCode;
        }

        public void setBICCode(String bICCode) {
        this.bICCode = bICCode;
        }

        public Integer getChequeNumber() {
        return chequeNumber;
        }

        public void setChequeNumber(Integer chequeNumber) {
        this.chequeNumber = chequeNumber;
        }

        public String getItemTypeCode() {
        return itemTypeCode;
        }

        public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
        }

        public Integer getTrackingNumber() {
        return trackingNumber;
        }

        public void setTrackingNumber(Integer trackingNumber) {
        this.trackingNumber = trackingNumber;
        }

        public String getPayerAccountNumber() {
        return payerAccountNumber;
        }

        public void setPayerAccountNumber(String payerAccountNumber) {
        this.payerAccountNumber = payerAccountNumber;
        }

        public Integer getBeneficiaryId() {
        return beneficiaryId;
        }

        public void setBeneficiaryId(Integer beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
        }

        public String getFundTransferTypeCode() {
        return fundTransferTypeCode;
        }

        public void setFundTransferTypeCode(String fundTransferTypeCode) {
        this.fundTransferTypeCode = fundTransferTypeCode;
        }

        public String getPaymentMethodCode() {
        return paymentMethodCode;
        }

        public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
        }

        public String getSettlementAccounNumber() {
        return settlementAccounNumber;
        }

        public void setSettlementAccounNumber(String settlementAccounNumber) {
        this.settlementAccounNumber = settlementAccounNumber;
        }

        public String getUserId() {
        return userId;
        }

        public void setUserId(String userId) {
        this.userId = userId;
        }

        public String getCreatedBy() {
        return createdBy;
        }

        public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        }
}
