package com.gmail.kolesnyk.zakhar;

import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    T selectById(I id);

    /**
     * method allow to save example of Entity
     *
     * @param object object of Entity
     */
    @Transactional
    void save(T object);

    /**
     * method allow to update example of Entity
     *
     * @param object object of Entity
     */
    @Transactional
    void update(T object);

    /**
     * method allow to remove example of Entity
     *
     * @param object object of Entity
     */
    @Transactional
    void remove(T object);

    /**
     * method allow get list of Entities
     *
     * @return List of Entities
     */
    @Transactional(readOnly = true)
    List<T> list();
}
