package com.diling;

import com.diling.model.Product;
import com.diling.model.Record;
import com.diling.repository.ProductRepository;
import com.diling.repository.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DiLingApplication.class)
public class RepositoryTest {
    private static Logger logger = LoggerFactory.getLogger(RepositoryTest.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Test
    public void test() {
        Record record = new Record();
        record.setData("12354698");
        record.setDate(new Date());
        record.setPort("8080");
        recordRepository.save(record);
    }

    @Test
    @Transactional
    public void getUnprocessedRecords() {
        List<Record> recordList = recordRepository.getUnprocessedRecords();
        logger.info("recordList=" + recordList);
    }

    @Test
    @Transactional
    public void getAllRecord() {
        List<Record> recordList = recordRepository.findAll();
        recordList.forEach(record -> {
            logger.info("product=" + record.getProduct());
        });
        logger.info("recordList=" + recordList);
    }

    @Test
    @Transactional
    public void createProduct() {
        Product product = new Product();
        productRepository.save(product);
        List<Record> recordList = recordRepository.findAll();
        recordList.forEach(record -> record.setProduct(product));
        recordRepository.saveAll(recordList);
        product.setRecords(recordList);
        productRepository.save(product);
    }

    @Test
    @Transactional
    public void getProduct() {
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> logger.info("records=" + product.getRecords()));
    }
}
