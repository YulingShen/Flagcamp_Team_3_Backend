package com.laiofferflagcamp.community.entity.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "maintenance") // 对应数据库表名
public class Maintenance implements Serializable {

    //https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1335458470150728375L;

    @Id // 主键
    @Column(name = "main_id") // 对应数据库表字段名
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    private long mainId;

    @Column(name = "user_id") // 外键
    private long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    public Maintenance() {
    }

    public Maintenance(long mainId, long userId, String status, Date date, String title, String description, String category) {
        this.mainId = mainId;
        this.userId = userId;
        this.status = status;
        this.date = date;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getMainId() {
        return mainId;
    }

    public void setMainId(long mainId) {
        this.mainId = mainId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

