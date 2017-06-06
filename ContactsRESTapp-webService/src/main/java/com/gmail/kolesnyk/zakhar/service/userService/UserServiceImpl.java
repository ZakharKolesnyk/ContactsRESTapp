package com.gmail.kolesnyk.zakhar.service.userService;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User create(User user) {
        userDao.save(user);
        return user;
    }

    public User getById(int idUser) {
        return userDao.selectById(idUser);
    }

    public User getByName(String name) {
        return userDao.selectUserName(name);
    }

    public User update(User user) {
        userDao.update(user);
        return user;
    }

    public boolean delete(User user) {
        return userDao.deleteById(user.getId());
    }
}
