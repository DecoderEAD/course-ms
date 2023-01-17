package com.ead.coursems.controllers;

import com.ead.coursems.clients.CourseClient;
import com.ead.coursems.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class CourseUserController {

    private final CourseClient courseClient;

    @GetMapping("/courses/{courseId}/users")
    public ResponseEntity<Page<UserDTO>> getAllUsersByCourse(@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
                                                             @PathVariable(value = "courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllUsersByCourses(pageable, courseId));
    }
}
