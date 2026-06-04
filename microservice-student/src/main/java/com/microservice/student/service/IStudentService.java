package com.microservice.student.service;

import com.microservice.student.dto.StudentByCourseResponse; // 1. Importa esto
import com.microservice.student.entities.Student;

import java.util.List;

public interface IStudentService {
    List<StudentByCourseResponse> findAllWithCourses();
    List<Student> findAll();
    Student findById(Long id);
    void save(Student student);
    List<Student> findByCourseId(Long courseId);

    // Este es el método que falta o está mal nombrado
    StudentByCourseResponse findStudentWithCourse(Long studentId);
}