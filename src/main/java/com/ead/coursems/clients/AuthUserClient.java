package com.ead.coursems.clients;

import com.ead.coursems.dto.CourseUserDTO;
import com.ead.coursems.dto.ResponsePageDTO;
import com.ead.coursems.dto.UserDTO;
import com.ead.coursems.services.UtilsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthUserClient {

    private final RestTemplate restTemplate;

    private final UtilsService utilsService;

    public Page<UserDTO> getAllUsersByCourses(Pageable pageable, UUID courseId){
        List<UserDTO> searchResult = null;
        ResponseEntity<ResponsePageDTO<UserDTO>> result = null;
        String url = utilsService.createUrlGetAllUsersByCourses(courseId, pageable);
        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);
        try{
            ParameterizedTypeReference<ResponsePageDTO<UserDTO>> responseType = new ParameterizedTypeReference<ResponsePageDTO<UserDTO>>() {};
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e){
            log.error("Error request /users {} ", e);
        }
        log.info("Ending request /users courseId {} ", courseId);
        return result.getBody();
    }

    public ResponseEntity<UserDTO> getOneUserById(UUID userId) {
        var url = utilsService.createUrlGetOneUserById(userId);
        return restTemplate.exchange(url, HttpMethod.GET, null, UserDTO.class);
    }

    public void postSubscriptionUserInCourse(UUID courseId, UUID userId) {
        var url = utilsService.createUrlPostSubscriptionUserInCourse(userId);
        var courseUserDTO = new CourseUserDTO();
        courseUserDTO.setUserId(userId);
        courseUserDTO.setCourseId(courseId);
        restTemplate.postForObject(url, courseUserDTO, String.class);
    }
}
