package com.ead.coursems.services.impl;

import com.ead.coursems.repositories.CourseUserRepository;
import com.ead.coursems.services.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseUserServiceImpl implements CourseUserService {

    private final CourseUserRepository courseUserRepository;
}
