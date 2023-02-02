package com.laiofferflagcamp.community.entity.reqeust;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequestBody {
    @JsonProperty("invoice_id")
    private String invoiceId;

    @JsonProperty("user_id")
    private String userId;

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getUserId() {
        return userId;
    }
}
