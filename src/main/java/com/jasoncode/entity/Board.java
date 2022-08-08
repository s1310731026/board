package com.jasoncode.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String publisher;
    private String context;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startdate",nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date startdate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enddate", nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date enddate = new Date();


    public Board() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
