package com.diling.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<Record> records;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Transient
    public boolean isPassed() {
        AtomicBoolean isPassed = new AtomicBoolean(false);
        if (getRecords() != null) {
            getRecords().forEach(record -> {
                if (!"0".equals(record.getData())) {
                    isPassed.set(true);
                }
            });
        }
        return isPassed.get();
    }
}