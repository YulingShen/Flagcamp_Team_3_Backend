package com.laiofferflagcamp.community.entity.reqeust;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequestBody {
    @JsonProperty("invoice_id")
    private String invoiceId;

    public String getInvoiceId() {
        return invoiceId;
    }
}
