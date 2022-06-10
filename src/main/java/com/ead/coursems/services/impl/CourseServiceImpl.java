package com.ead.coursems.services.impl;

import com.ead.coursems.repositories.CourseRepository;
import com.ead.coursems.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
}
