package org.example.service.impl;

import org.example.base.service.impl.BaseServiceImpl;
import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;

public class CourseServiceImpl extends BaseServiceImpl<Integer, Course, CourseRepository>
                               implements CourseService {
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }
}
