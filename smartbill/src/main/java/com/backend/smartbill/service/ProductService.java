package com.backend.smartbill.service;

import com.backend.smartbill.model.ProductModel;
import com.backend.smartbill.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<ProductModel> findAll() {
        List<ProductModel> list;
        try{
            list = productRepository.findAll();
        }catch (Exception ex){
            throw ex;
        }
        return list;
    }
}
