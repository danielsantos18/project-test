package com.backend.smartbill.controller;

import com.backend.smartbill.model.ProductModel;
import com.backend.smartbill.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/")
public class ProductController {

    @Autowired
    IProductService iProductService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<ProductModel> products = this.iProductService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
