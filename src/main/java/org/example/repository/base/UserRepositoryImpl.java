package org.example.repository.base;

import org.example.base.repository.impl.BaseRepositoryImpl;
import org.example.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public abstract class UserRepositoryImpl<T extends User> extends BaseRepositoryImpl<Integer, T> implements UserRepository<T> {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
    @Override
    public boolean existsByUsername(String username) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(u) from " + getEntityClass().getSimpleName() + " u where u.username = :username",
                Long.class
        );
        query.setParameter("username", username);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsByMobileNumber(String mobileNumber) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(u) from " + getEntityClass().getSimpleName() + " u where u.mobileNumber = :mobileNumber",
                Long.class
        );
        query.setParameter("mobileNumber", mobileNumber);
        return query.getSingleResult() > 0;
    }

    @Override
    public Optional<T> findByUsername(String username) {
        TypedQuery<T> query = entityManager.createQuery(
                "select u from " + getEntityClass().getSimpleName() + " u where u.username = :username",
                getEntityClass()
        );
        query.setParameter("username", username);
        List<T> resultList = query.getResultList();
        if (resultList.size() == 1) {
            return Optional.of(
                    resultList.get(0)
            );
        } else if (resultList.size() == 0) {
            return Optional.empty();
        }
        throw new NonUniqueResultException();
    }
    @Override
    public boolean existByUserNameAndPassword(String userName, String password) {
        try {
            String sql= "select count(u.userName) from "
                    +getEntityClass().getSimpleName()
                    +" u where u.userName = :userName And u.password = :password";
            TypedQuery<Long> query = entityManager.createQuery(sql,Long.class);
            query.setParameter("userName",userName);
            query.setParameter("password",password);
            return query.getSingleResult()>0;
        }catch (Exception e){
            System.out.println(new IllegalArgumentException(" user name and password not found").getMessage());
            return false;
        }
    }

    @Override
    public T findByUserName(String userName) {
        String hql = "SELECT t FROM "+getEntityClass().getSimpleName()+ " t WHERE t.userName = :userName";
        TypedQuery<T> query = entityManager.createQuery(hql, getEntityClass());
        query.setParameter("userName" ,userName);
        return query.getSingleResult();
    }
}
