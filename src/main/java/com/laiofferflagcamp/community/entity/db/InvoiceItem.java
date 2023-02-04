package com.laiofferflagcamp.community.entity.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoice")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceItem {

    @Id
    @Column(name = "invoice_id")
    @JsonProperty("invoiceId")
    private String invoiceId;

    @Column(name = "payment_id")
    @JsonProperty("payment_id")
    private String paymentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payee")
    @JsonProperty("payee")
    private User payee;

    @Column(name = "invoice_date")
    @JsonProperty("invoiceDate")
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date invoiceDate;

    @Column(name = "due_date")
    @JsonProperty("dueDate")
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date dueDate;

    @JsonProperty("invoiceAmount")
    private Integer amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("description")
    private String description;

    public String getInvoiceId() {
        return invoiceId;
    }

    public User getPayee() {
        return payee;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
