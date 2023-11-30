package org.example.base.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.base.repository.BaseRepository;
import org.example.base.service.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
@RequiredArgsConstructor
public class BaseServiceImpl<ID extends Serializable,T extends BaseEntity<ID>,R extends BaseRepository<ID,T>>
                            implements BaseService<ID,T>{
    protected final R repository;



    @Override
    public T saveOrUpdate(T entity) {
        return repository.saveOrUpdate(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.delete(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }
    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public void beginTransaction() {
        repository.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        repository.commitTransaction();
    }

    @Override
    public void rollback() {
        repository.rollBack();
    }
}
