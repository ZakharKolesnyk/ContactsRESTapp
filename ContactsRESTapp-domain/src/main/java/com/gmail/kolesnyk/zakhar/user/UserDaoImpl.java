package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class UserDaoImpl extends AbstractDao<User, Integer> implements UserDao {


    @Override
    public User selectByUserName(String userName) {
        return (User) sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM users WHERE user_name = :userName")
                .addEntity(User.class).setParameter("userName", userName).uniqueResult();
    }

    @Override
    public boolean deleteById(int id) {
        return sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM users WHERE id = :id")
                .addEntity(User.class).setParameter("id", id).executeUpdate() == 1;
    }

    @Override
    public boolean isExistName(String userName) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(id)  FROM users WHERE user_name = :userName")
                .setParameter("userName", userName).uniqueResult()).intValue() == 1;
    }

    @Override
    public boolean isExistPhone(String phone) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(id)  FROM users WHERE phone = :phone")
                .setParameter("phone", phone).uniqueResult()).intValue() == 1;
    }

    @Override
    public boolean isExistId(Integer id) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(id)  FROM users WHERE id = :id")
                .setParameter("id", id).uniqueResult()).intValue() == 1;
    }
}
