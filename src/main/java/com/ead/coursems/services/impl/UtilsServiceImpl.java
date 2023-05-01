package com.ead.coursems.services.impl;

import com.ead.coursems.services.UtilsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Value("${ead.api.url.auth-user}")
    String REQUEST_URL_AUTH_USER;

    @Override
    public String createUrlGetAllUsersByCourses(UUID courseId, Pageable pageable) {
        return REQUEST_URL_AUTH_USER + "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replace(": ", ",");
    }

    @Override
    public String createUrlGetOneUserById(UUID userId) {
        return REQUEST_URL_AUTH_USER + "/users/" + userId;
    }

    @Override
    public String createUrlPostSubscriptionUserInCourse(UUID userId) {
        return REQUEST_URL_AUTH_USER + "/users/" + userId + "/courses/subscription";
    }

    @Override
    public String createDeleteCourseInAuthUser(UUID courseId) {
        return REQUEST_URL_AUTH_USER + "/users/courses/" + courseId;
    }
}
