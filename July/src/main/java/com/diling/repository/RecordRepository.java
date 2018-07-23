package com.diling.repository;

import com.diling.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    @Query(value = "SELECT * FROM RECORD WHERE PRODUCT_ID is NULL", nativeQuery = true)
    List<Record> getUnprocessedRecords();
}
