package com.iflytek.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iflytek.entity.AdminUser;

public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsernameAndPassword(String username, String pwd);
}
