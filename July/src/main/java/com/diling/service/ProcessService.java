package com.diling.service;

import com.diling.model.Product;
import com.diling.model.Record;
import com.diling.repository.ProductRepository;
import com.diling.repository.RecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessService {
    private static Logger logger = LoggerFactory.getLogger(ProcessService.class);

    @Value("${monitoring.delay}")
    private int delay;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    public void saveRecord(String data, Date date, String port) {
        Record record = new Record();
        record.setData(data);
        record.setDate(date);
        record.setPort(port);
        recordRepository.save(record);
    }

    @Transactional
    public void processRecords() {
        List<Record> recordList = recordRepository.getUnprocessedRecords();
        List<Product> productList = new ArrayList<>();
        recordList.forEach(record -> {
            Product product = null;
            Optional<Product> optionalProduct = productList.stream().filter(p -> (record.getDate().getTime() - p.getDate().getTime()) < delay).findFirst();
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                Optional<Record> optionalRecord = product.getRecords().stream().filter(r -> r.getPort().equals(record.getPort())).findFirst();
                if (optionalRecord.isPresent()) {
                    product = createNewProduct(record);
                    productList.add(product);
                } else {
                    product.getRecords().add(record);
                }
            } else {
                product = createNewProduct(record);
                productList.add(product);
            }
            record.setProduct(product);
            productRepository.save(product);
        });
        recordRepository.saveAll(recordList);
    }

    private Product createNewProduct(Record record) {
        Product product = new Product();
        product.setRecords(new ArrayList<Record>());
        product.getRecords().add(record);
        product.setDate(record.getDate());
        return product;
    }
}
