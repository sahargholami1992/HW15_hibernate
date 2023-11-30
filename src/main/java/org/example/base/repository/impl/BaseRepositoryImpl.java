package org.example.base.repository.impl;


import lombok.AllArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.base.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
@AllArgsConstructor
public abstract class BaseRepositoryImpl<ID extends Serializable,T extends BaseEntity<ID>> implements BaseRepository<ID,T> {
    protected EntityManager entityManager;
    @Override
    public T saveOrUpdate(T base) {
        beginTransaction();
        base = saveWithOutTransaction(base);
        commitTransaction();
        entityManager.clear();
        return base;
    }
    @Override
    public void commitTransaction() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();
    }

    protected T saveWithOutTransaction(T base) {
        if (base.getId() == null)
            entityManager.persist(base);
        else
            base = entityManager.merge(base);
        return base;
    }
    @Override
    public void beginTransaction() {
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
    }
    @Override
    public long count() {
        return entityManager.createQuery(
                "select count(t) from " + getEntityClass().getSimpleName() + " t",
                Long.class
        ).getSingleResult();
    }

    @Override
    public void delete(ID id) {
        beginTransaction();
        entityManager.find(getEntityClass(),id);
        entityManager.remove(id);
        commitTransaction();
    }
    @Override
    public boolean existsById(ID id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(t) from " + getEntityClass().getSimpleName() + " t where t.id = :id", Long.class
        );
        query.setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(),id));
    }

    public abstract Class<T> getEntityClass();

    @Override
    public Collection<T> findAll() {

        return entityManager.createQuery(
                "FROM " + getEntityClass().getSimpleName(), getEntityClass()
        ).getResultList();

    }
    @Override
    public void rollBack() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().rollback();
    }
}
