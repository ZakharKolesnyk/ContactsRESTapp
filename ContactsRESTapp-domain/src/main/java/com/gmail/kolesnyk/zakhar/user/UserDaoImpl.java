package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * class implements and extends methods that need to ORM relations with {@link User} class
 */
@Repository
public class UserDaoImpl extends AbstractDao<User, Integer> implements UserDao {


    @Override
    public User selectUserName(String userName) {
        return (User) sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM users WHERE user_name = :userName")
                .addEntity(User.class).setParameter("userName", userName).uniqueResult();
    }

    @Override
    public boolean deleteById(int id) {
        return sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM users WHERE id = :id")
                .addEntity(User.class).setParameter("id", id).executeUpdate() == 1;
    }
}
