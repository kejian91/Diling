package com.diling.service;

import com.diling.model.Product;
import com.diling.model.Result;
import com.diling.repository.ProductRepository;
import com.diling.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QueryService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProcessService processService;

    public Result getResult() {
        Result result = new Result();
        processService.processRecords();
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> {
            result.addPassNumber();
            if (product.isPassed()) {
                result.addIdentifiedNumber();
            } else {
                result.addUnidentifiedNumber();
            }
        });
        if (result.getPassNumber() != 0) {
            double rate = new Integer(result.getIdentifiedNumber()).doubleValue()
                    / new Integer(result.getPassNumber()).doubleValue();
            DecimalFormat dFormat = new DecimalFormat("#.00");
            dFormat.setRoundingMode(RoundingMode.HALF_EVEN);
            result.setIdentifiedRate(Double.parseDouble(dFormat.format(rate)));
        }
        return result;
    }

    public List<Product> getProducts(Date date) {
        return productRepository.findProductByDate(sdf.format(date));
    }
}
