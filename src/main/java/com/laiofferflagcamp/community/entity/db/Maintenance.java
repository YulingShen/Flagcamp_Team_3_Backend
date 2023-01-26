package com.laiofferflagcamp.community.entity.db;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "maintenance")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Maintenance {

    //https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("main_id")
    private String mainId;

    @JsonProperty("status")
    private String status;

    @Column(name = "date")
    @JsonProperty("date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonProperty("user_id")
    private User userId;

    public String getMaintenanceId() {
        return mainId;
    }

    public void setMaintenanceId(String mainId) {
        this.mainId = mainId;
    }

    public User getRequester() {
        return userId;
    }

    public void setRequester(User userId) {
        this.userId = userId;
    }

    public Date getRequestDate() {
        return date;
    }

    public void setRequestDate(Date date) {
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String status) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

