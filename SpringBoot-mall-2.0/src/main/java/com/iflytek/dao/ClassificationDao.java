package com.iflytek.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iflytek.entity.Classification;

import java.util.List;

public interface ClassificationDao extends JpaRepository<Classification, Integer> {
    List<Classification> findByType(int type);

    Page<Classification> findByType(int type, Pageable pageable);

    List<Classification> findByParentId(int cid);
    
    @Query(value = "select id  from  `classification`  where cname like '%?1%' ",nativeQuery = true)
    List <Classification> select(String  cname);
}
