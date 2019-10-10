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
public class BatchItemClass {

    @SerializedName("Request")
    @Expose
    private BatchItemRequest request;

    public BatchItemRequest getRequest() {
    return request;
    }

    public void setRequest(BatchItemRequest request) {
    this.request = request;
    }
}