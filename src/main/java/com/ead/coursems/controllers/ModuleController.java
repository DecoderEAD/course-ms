package com.ead.coursems.controllers;

import com.ead.coursems.dto.ModuleDTO;
import com.ead.coursems.models.CourseModel;
import com.ead.coursems.models.ModuleModel;
import com.ead.coursems.services.CourseService;
import com.ead.coursems.services.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;
    private final CourseService courseService;

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<Object> saveCourse(@PathVariable(value = "courseId") UUID courseId,
                                             @RequestBody @Valid ModuleDTO moduleDTO) {
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        }
        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleDTO, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setCourse(courseModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleModel));
    }

    @DeleteMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable(value = "moduleId") UUID moduleId) {
        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if(!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
        }
        moduleService.delete(moduleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Module deleted successfully.");
    }

    @PutMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> updateModule(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable(value = "moduleId") UUID moduleId,
                                               @RequestBody @Valid ModuleDTO moduleDTO) {
        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if(!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
        }
        var moduleModel = moduleModelOptional.get();
        moduleModel.setTitle(moduleDTO.getTitle());
        moduleModel.setDescription(moduleDTO.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.save(moduleModel));
    }

    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<List<ModuleModel>> getAllModules(@PathVariable("courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAllByCourse(courseId));
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> getOneModule(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable(value = "moduleId") UUID moduleId) {
        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if(!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(moduleModelOptional.get());
    }
}
