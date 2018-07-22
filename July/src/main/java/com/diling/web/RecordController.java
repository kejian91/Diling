package com.diling.web;

import com.diling.model.Record;
import com.diling.model.Result;
import com.diling.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/records")
    public List<Record> getRecords() {
        return recordRepository.findAll();
    }

    @GetMapping("/result")
    public Result getResult() {
        Result result = new Result();
        return result;
    }
}
