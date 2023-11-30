package org.example.service.impl;

import org.example.base.service.impl.BaseServiceImpl;
import org.example.entity.Lesson;
import org.example.repository.LessonRepository;
import org.example.service.LessonService;

public class LessonServiceImpl extends BaseServiceImpl<Integer, Lesson, LessonRepository>
                               implements LessonService {
    public LessonServiceImpl(LessonRepository repository) {
        super(repository);
    }
}
