package com.laiofferflagcamp.community.entity.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @Column(name = "user_id")
    @JsonProperty("username")
    private String userId;

    @JsonProperty("password")
    private String password;

    @Column(name = "first_name")
    @JsonProperty("firstname")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("lastname")
    private String lastName;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @Column(name = "apartment")
    @JsonProperty("apartment")
    private String apartment;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "payee")
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
}
