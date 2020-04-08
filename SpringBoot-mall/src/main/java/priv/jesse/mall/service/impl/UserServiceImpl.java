package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.UserDao;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User findById(int id) {
        return userDao.getOne(id);
    }

    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    public List<User> findAllExample(Example<User> example) {
        return userDao.findAll(example);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public int create(User user) {
        return userDao.save(user).getId();
    }

    public void delById(int id) {
        userDao.delete(userDao.getOne(id));
    }

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 检查登录
     *
     * @param username
     * @param password
     * @return
     */
    public User checkLogin(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
