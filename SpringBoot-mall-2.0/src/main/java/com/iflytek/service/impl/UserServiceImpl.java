package com.iflytek.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.iflytek.dao.UserDao;
import com.iflytek.entity.Order;
import com.iflytek.entity.User;
import com.iflytek.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.getOne(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public List<User> findAllExample(Example<User> example) {
        return userDao.findAll(example);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public int create(User user) {
        return userDao.save(user).getId();
    }

    @Override
    public void delById(int id) {
        userDao.delete(userDao.getOne(id));
    }


    @Override
    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

   
    @Override
    public User checkLogin(String username,String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

	@Override
	public User checkLogin1(String name, String phone) {
		// TODO Auto-generated method stub
		return userDao.findByNameAndPhone(name, phone);
	}

	@Override
	public void updatepassword(int id, String password) {
		// TODO Auto-generated method stub
		 User user = userDao.getOne(id);
	        user.setPassword(password);
	      //  orderDao.save(order)
	        userDao.save(user);
	}
}
