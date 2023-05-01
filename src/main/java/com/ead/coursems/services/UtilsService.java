package com.ead.coursems.services;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilsService {

    String createUrlGetAllUsersByCourses(UUID userId, Pageable pageable);

    String createUrlGetOneUserById(UUID userId);

    String createUrlPostSubscriptionUserInCourse(UUID userId);

    String createDeleteCourseInAuthUser(UUID courseId);
}
