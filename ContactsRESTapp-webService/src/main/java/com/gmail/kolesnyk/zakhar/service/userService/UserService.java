package com.gmail.kolesnyk.zakhar.service.userService;

import com.gmail.kolesnyk.zakhar.service.Service;
import com.gmail.kolesnyk.zakhar.user.User;


/**
 * The {@code UserService} class used for operations associated with User
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.service.Service
 * @since JDK1.8
 */
public interface UserService extends Service {

    /**
     * method allow to create new {@link User} in system
     *
     * @param user example of new User
     * @return example of created User
     */
    User create(User user);

    /**
     * method allow to get example of {@link User} by it ID
     *
     * @param idUser ID User
     * @return {@link User} example of User
     */
    User getById(int idUser);

    /**
     * method allow to get example of {@link User} by it Name
     *
     * @param name name of User
     * @return {@link User} example of User
     */
    User getByName(String name);

    /**
     * method allow to update {@link User} fields in system by it new example
     *
     * @param user exmple of User
     * @return example of updated User
     */
    User update(User user);

    /**
     * method allow to remove {@link User} from system
     *
     * @param user example of User
     */
    void delete(User user);

    /**
     * method allow to remove {@link User} from system
     *
     * @param id ID of User
     */
    void delete(Integer id);
}
