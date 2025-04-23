package com.project.controller;

import com.project.dtos.PageResponseDto;
import com.project.entity.Product;
import com.project.service.CustomerService;
import com.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-products")
    public ResponseEntity<?> addProducts(@RequestBody List<Product> products){
        if (productService.addProducts(products)!=null){
            return new ResponseEntity<>(products, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Products not added", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-products")
    private ResponseEntity<?> getProducts(@RequestParam int pageNumber,@RequestParam int pageSize){
        PageResponseDto pageResponseDto=productService.getProducts(pageNumber,pageSize);
        if(pageResponseDto!=null){
            return ResponseEntity.ok(pageResponseDto);
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }

}
