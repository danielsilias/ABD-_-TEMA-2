package com.microservice.student.client;

import com.microservice.student.dto.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-course") // Nombre registrado en Eureka
public interface CourseClient {

    // La ruta completa que el microservicio de cursos espera
    @GetMapping("/api/course/search/{id}")
    CourseDTO findCourseById(@PathVariable("id") Long id);
}