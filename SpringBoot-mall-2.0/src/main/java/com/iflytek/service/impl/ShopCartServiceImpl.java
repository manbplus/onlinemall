package com.iflytek.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iflytek.entity.OrderItem;
import com.iflytek.entity.Product;
import com.iflytek.entity.User;
import com.iflytek.service.ProductService;
import com.iflytek.service.ShopCartService;
import com.iflytek.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ProductService productService;


    @Override
    public void addCart(int productId, HttpServletRequest request) throws Exception {
        String num = request.getParameter("num");
        Integer number=0;
        if(num!=null){
            number=Integer.parseInt(num);
        }
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
        List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        if (productIds == null) {
            productIds = new ArrayList<>();
            request.getSession().setAttribute(NAME_PREFIX + loginUser.getId(), productIds);
            productIds.add(productId);
        }else{
            Iterator<Integer> it = productIds.iterator();
            boolean flag=true;
            while(it.hasNext()){
                Integer temp = it.next();
                if(temp.intValue()==productId){
                    it.remove();
                    flag=false;
                }
            }
            for(int i=0;i<number;i++){
                productIds.add(productId);
            }
            if(flag){
                productIds.add(productId);
            }
        }


    }


    @Override
    public void remove(int productId, HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
        List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        Iterator<Integer> iterator = productIds.iterator();
        while (iterator.hasNext()) {
            if (productId == iterator.next()) {
                iterator.remove();
            }
        }
    }


    @Override
    public List<OrderItem> listCart(HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("未登录！请重新登录");
        List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        // key: productId value:OrderItem
        Map<Integer, OrderItem> productMap = new HashMap<>();
        if (productIds == null){
            return new ArrayList<>();
        }
        // 遍历List中的商品id，每个商品Id对应一个OrderItem
        for (Integer productId : productIds) {

            if (productMap.get(productId) == null) {
                Product product = productService.findById(productId);
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setProductId(productId);
                orderItem.setCount(1);
                orderItem.setSubTotal(product.getShopPrice());
                productMap.put(productId, orderItem);
            } else {
                Product product = productService.findById(productId);
                OrderItem orderItem = productMap.get(productId);
                int count = orderItem.getCount();
                orderItem.setCount(++count);
                Double subTotal = orderItem.getSubTotal();
                orderItem.setSubTotal(orderItem.getSubTotal()+product.getShopPrice());
                productMap.put(productId, orderItem);
            }
        }
        List<OrderItem> orderItems = new ArrayList<>(productMap.values());
        return orderItems;
    }
}
