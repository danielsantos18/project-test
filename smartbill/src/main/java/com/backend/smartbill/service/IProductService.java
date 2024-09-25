package com.backend.smartbill.service;

import com.backend.smartbill.model.ProductModel;
import java.util.List;

public interface IProductService {
    public List<ProductModel> findAll();
}
