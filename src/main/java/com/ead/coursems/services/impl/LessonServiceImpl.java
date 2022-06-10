package com.ead.coursems.services.impl;

import com.ead.coursems.repositories.LessonRepository;
import com.ead.coursems.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
}
