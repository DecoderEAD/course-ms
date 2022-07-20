package com.ead.coursems.repositories;

import com.ead.coursems.models.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID>, JpaSpecificationExecutor<LessonModel> {

    @Query(value = "SELECT * FROM tb_lessons where module_module_id = :moduleId", nativeQuery = true)
    List<LessonModel> findAllLessonsIntoModule(@Param("moduleId") UUID moduleId);

    @Query(value = "SELECT * FROM tb_lessons where module_module_id = :moduleId " +
            " and lesson_id = :lessonId ", nativeQuery = true)
    Optional<LessonModel> findLessonIntoModule(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId);
}
