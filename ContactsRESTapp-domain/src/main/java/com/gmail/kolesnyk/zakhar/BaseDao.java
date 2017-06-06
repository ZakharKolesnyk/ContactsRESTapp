package com.gmail.kolesnyk.zakhar;

import java.util.List;

/**
 * The {@code com.gmail.kolesnyk.zakhar.BaseDao} implements main methods that required for ORM relations
 *
 * @author Kolesnyk Zakhar
 * @since JDK1.8
 */
public interface BaseDao<T, I> {

    /**
     * method allow to get example of Entity by it ID
     *
     * @param id ID of Entity
     * @return example of Entity
     */
    T selectById(I id);

    /**
     * method allow to save example of Entity
     *
     * @param object object of Entity
     */
    void save(T object);

    /**
     * method allow to update example of Entity
     *
     * @param object object of Entity
     */
    void update(T object);

    /**
     * method allow to remove example of Entity
     *
     * @param object object of Entity
     */
    void remove(T object);

    /**
     * method allow get list of Entities
     *
     * @return List of Entities
     */
    List<T> list();
}
