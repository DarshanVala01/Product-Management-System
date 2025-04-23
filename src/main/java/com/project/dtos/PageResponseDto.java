package com.project.dtos;

import com.project.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponseDto {

    private List<Product> products;

    private int totalPages;

    private int size;

    private int pageNumber;
}
