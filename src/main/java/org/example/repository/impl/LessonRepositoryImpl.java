package org.example.repository.impl;

import org.example.base.repository.impl.BaseRepositoryImpl;
import org.example.entity.Lesson;
import org.example.repository.LessonRepository;

import javax.persistence.EntityManager;

public class LessonRepositoryImpl extends BaseRepositoryImpl<Integer, Lesson> implements LessonRepository {
    public LessonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Lesson> getEntityClass() {
        return Lesson.class;
    }


}
