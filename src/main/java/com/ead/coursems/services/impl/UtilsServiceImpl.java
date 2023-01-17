package com.ead.coursems.services.impl;

import com.ead.coursems.services.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {

    String REQUEST_URL_COURSE = "http://localhost:8087";

    @Override
    public String createUrlGetAllUsersByCourses(UUID courseId, Pageable pageable) {
        return REQUEST_URL_COURSE + "/users?courseId=" + courseId + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replace(": ", ",");
    }
}
