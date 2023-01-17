package com.ead.coursems.services;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilsService {

    String createUrlGetAllUsersByCourses(UUID userId, Pageable pageable);
}
