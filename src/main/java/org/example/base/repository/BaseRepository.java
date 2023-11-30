package org.example.base.repository;

import org.example.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<ID extends Serializable,T extends BaseEntity<ID>> {
    T saveOrUpdate(T base);

    void commitTransaction();

    void beginTransaction();

    long count();

    boolean existsById(ID id);

    void delete(ID id);

    Optional<T> findById(ID id);

    Collection<T> findAll();


    void rollBack();
}
