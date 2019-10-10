/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totagotech.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Totago_User3
 */
public class BatchHeaderResponse {

        @SerializedName("ResponseCode")
        @Expose
        private Integer responseCode;
        @SerializedName("ResponseMessage")
        @Expose
        private String responseMessage;
        @SerializedName("TransactionBatchId")
        @Expose
        private int transactionBatchId;

        public Integer getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(Integer responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public int getTransactionBatchId() {
            return transactionBatchId;
        }

        public void setTransactionBatchId(int transactionBatchId) {
            this.transactionBatchId = transactionBatchId;
        }

    }
