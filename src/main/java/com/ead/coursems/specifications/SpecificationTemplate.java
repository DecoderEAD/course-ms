package com.ead.coursems.specifications;

import com.ead.coursems.models.CourseModel;
import com.ead.coursems.models.CourseUserModel;
import com.ead.coursems.models.LessonModel;
import com.ead.coursems.models.ModuleModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

public class SpecificationTemplate {

    @And({
            @Spec(path = "courseLevel", spec = Equal.class),
            @Spec(path = "courseStatus", spec = Equal.class),
            @Spec(path = "name", spec = Like.class)
    })
    public interface CourseSpec extends Specification<CourseModel> {
    }

    @Spec(path = "title", spec = Like.class)
    public interface ModuleSpec extends Specification<ModuleModel> {
    }

    @Spec(path = "title", spec = Like.class)
    public interface LessonSpec extends Specification<LessonModel> {
    }

    public static Specification<ModuleModel> moduleCourseId(final UUID courseId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            Root<ModuleModel> module = root;
            Root<CourseModel> course = criteriaQuery.from(CourseModel.class);
            Expression<Collection<ModuleModel>> courseModules = course.get("modules");
            return criteriaBuilder.and(criteriaBuilder.equal(course.get("courseId"), courseId), criteriaBuilder.isMember(module, courseModules));
        };
    }

    public static Specification<LessonModel> lessonModuleId(final UUID moduleId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            Root<LessonModel> lesson = root;
            Root<ModuleModel> module = criteriaQuery.from(ModuleModel.class);
            Expression<Collection<LessonModel>> moduleLessons = module.get("lessons");
            return criteriaBuilder.and(criteriaBuilder.equal(module.get("moduleId"), moduleId), criteriaBuilder.isMember(lesson, moduleLessons));
        };
    }

    public static Specification<CourseModel> courseUserId(final UUID userId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            Join<CourseModel, CourseUserModel> courseProd = root.join("coursesUsers");
            return criteriaBuilder.equal(courseProd.get("userId"), userId);
        });
    }
}
