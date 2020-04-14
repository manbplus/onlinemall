package com.iflytek.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.iflytek.entity.Order;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update `order` o set o.state=?1 where o.id=?2",nativeQuery = true)
    void updateState(int state,int id);

    List<Order> findByUserId(int userId);
}
