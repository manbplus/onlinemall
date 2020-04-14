package com.iflytek.service;

import com.iflytek.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ShopCartService {

    String NAME_PREFIX = "shop_cart_";

   
    void addCart(int productId, HttpServletRequest request) throws Exception;

    
    void remove(int productId, HttpServletRequest request) throws Exception;

  
    List<OrderItem> listCart(HttpServletRequest request) throws Exception;
}
