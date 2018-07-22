package com.diling.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Record {
    @Id
    @GeneratedValue
    private Long id;
    private String port;
    private String data;
    private Date date;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "product_id")
    private Product product;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
