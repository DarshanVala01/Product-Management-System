package com.project.service;

import com.project.configuration.ProductThresHoldConfig;
import com.project.dtos.PageResponseDto;
import com.project.entity.Product;
import com.project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductThresHoldConfig productThresHoldConfig;

    public List<Product> addProducts(List<Product> products) {
        try {
            return this.productRepo.saveAll(products);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public PageResponseDto getProducts(int offset, int pageSize){
        try {
            Pageable pageable= PageRequest.of(offset,pageSize);
            Page<Product> page = this.productRepo.findAll(pageable);

            PageResponseDto pageResponseDto = new PageResponseDto();

            pageResponseDto.setProducts(page.getContent());
            pageResponseDto.setPageNumber(page.getNumber());
            pageResponseDto.setTotalPages(page.getTotalPages());
            pageResponseDto.setSize(pageSize);

            return pageResponseDto;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //this method check whether quantity is less thea threshold value
    public boolean isLessThenThresHold(int inventoryCount) {
        return inventoryCount <= this.productThresHoldConfig.getThresholdValue();
    }


}
