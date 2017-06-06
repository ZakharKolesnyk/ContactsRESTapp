package com.gmail.kolesnyk.zakhar;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public abstract class AbstractDao<T, I extends Serializable> implements BaseDao<T, I> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T selectById(I id) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    @Override
    public void save(T object) {
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(T object) {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public void remove(T object) {
        sessionFactory.getCurrentSession().delete(object);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<T> list() {
        return sessionFactory.getCurrentSession().createCriteria(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
