package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.BaseDao;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    User selectByUserName(String userName);

    /**
     * method allow to get example of {@link User} by it userName
     *
     * @param id ID of User
     * @return example of Entity
     */
    @Transactional
    boolean deleteById(int id);

    /**
     * method allow to know is existing such name in database or not
     *
     * @param userName name of User
     * @return true if name is exist, false is name not exist
     */
    @Transactional(readOnly = true)
    boolean isExistName(String userName);

    /**
     * method allow to know is existing such phone in database or not
     *
     * @param phone phone of User
     * @return true if phone is exist, false is phone not exist
     */
    @Transactional(readOnly = true)
    boolean isExistPhone(String phone);

    /**
     * method allow to know is existing such ID in database or not
     *
     * @param id ID of User
     * @return true if ID is exist, false is ID not exist
     */
    @Transactional(readOnly = true)
    boolean isExistId(Integer id);
}
