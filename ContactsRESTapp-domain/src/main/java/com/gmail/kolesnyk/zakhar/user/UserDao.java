package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.BaseDao;

/**
 * The {@code UserDao} class DAO for entity {@link User}
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.AbstractDao
 * @see com.gmail.kolesnyk.zakhar.user.UserDaoImpl
 * @since JDK1.8
 */
public interface UserDao extends BaseDao<User, Integer> {

    /**
     * method allow to get example of {@link User} by it userName
     *
     * @param userName userName of User
     * @return example of Entity
     */
    User selectUserName(String userName);

    /**
     * method allow to get example of {@link User} by it userName
     *
     * @param id ID of User
     * @return example of Entity
     */
    boolean deleteById(int id);
}
