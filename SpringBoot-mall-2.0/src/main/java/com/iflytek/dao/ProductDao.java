package com.iflytek.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iflytek.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
  
    List<Product> findByCsid(int csid, Pageable pageable);

    List<Product> findByCsidIn(List<Integer> csids,Pageable pageable);

   
    List<Product> findByTitleIsLike(String keyword, Pageable pageable);

    List<Product> findByPdateAfter(Date date, Pageable pageable);

   
    List<Product> findByIsHot(int isHot,Pageable pageable);

  
    @Query(value = "SELECT * FROM (SELECT  * FROM product ORDER BY pdate DESC limit 0,24) a /*#pageable*/",nativeQuery = true)
    List<Product> findNew(Pageable pageable);

   @Query(value="select * from product where title =?1 ",nativeQuery =true)
   List<Product> seachName(String keyword,String title);

}
