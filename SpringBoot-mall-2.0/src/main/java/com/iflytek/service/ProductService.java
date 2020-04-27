package com.iflytek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.iflytek.entity.Product;
import com.iflytek.entity.pojo.ResultBean;

import java.util.List;

public interface ProductService {
  
    Product findById(int id);

   
    Page<Product> findAll(Pageable pageable);

  
    List<Product> findHotProduct();

  
    List<Product> findNewProduct(Pageable pageable);

   
    List<Product> findByCid(int cid,Pageable pageable);

   
    List<Product> findByCsid(int csid,Pageable pageable);

    void update(Product product);

   
    int create(Product product);

   
    void delById(int id);
     

}
