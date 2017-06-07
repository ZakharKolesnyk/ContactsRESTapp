package com.gmail.kolesnyk.zakhar.service.userService;

import com.gmail.kolesnyk.zakhar.service.userService.exeption.NoChangesAfterRequestException;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User create(User user) {
        if (userDao.isExistName(user.getUserName())) {
            throw new NoChangesAfterRequestException("Such name of user already exist in system");
        }
        if (userDao.isExistPhone(user.getPhone())) {
            throw new NoChangesAfterRequestException("Such phone of user already exist in system");
        }
        userDao.save(user);
        return user;
    }

    public User getById(int idUser) {
        User user = userDao.selectById(idUser);
        if (user == null) {
            throw new NoChangesAfterRequestException("User not found");
        }
        return user;
    }

    public User getByName(String name) {
        User user = userDao.selectByUserName(name);
        if (user == null) {
            throw new NoChangesAfterRequestException("User not found");
        }
        return user;
    }

    public User update(User user) {
        if (!userDao.isExistId(user.getId())) {
            throw new NoChangesAfterRequestException("User with such ID not found");
        }
        userDao.update(user);
        return user;
    }

    public void delete(User user) {
        if (!userDao.deleteById(user.getId())) {
            throw new NoChangesAfterRequestException("Deleting not happens");
        }
    }

    public void delete(Integer id) {
        if (!userDao.deleteById(id)) {
            throw new NoChangesAfterRequestException("Deleting not happens");
        }
    }
}
