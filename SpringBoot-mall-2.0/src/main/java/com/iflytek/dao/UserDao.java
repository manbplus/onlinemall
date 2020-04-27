package com.iflytek.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iflytek.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
   
    User findByUsernameAndPassword(String username, String password);

   
    List<User> findByUsername(String username);
    
    User findByNameAndPhone(String name, String phone);

    @Query(value = "update `user` set password=?1 where id=?2",nativeQuery = true)
    void updatepassword(String password,int id);

}
