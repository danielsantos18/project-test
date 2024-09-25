package com.backend.smartbill.repository;

import com.backend.smartbill.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAll();
}
