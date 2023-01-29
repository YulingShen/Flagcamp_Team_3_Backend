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
    @JsonProperty("invoice_id")
    private String invoiceId;

    @Column(name = "payment_id")
    @JsonProperty("payment_id")
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "payee", referencedColumnName = "user_id")
    @JsonProperty("payee")
    private User payee;

    @Column(name = "invoice_date")
    @JsonProperty("invoice_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date invoiceDate;

    @Column(name = "due_data")
    @JsonProperty("due_data")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("description")
    private String description;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
