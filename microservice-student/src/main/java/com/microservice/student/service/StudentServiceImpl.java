package com.microservice.student.service;

import com.microservice.student.client.CourseClient;
import com.microservice.student.dto.CourseDTO;
import com.microservice.student.dto.StudentByCourseResponse;
import com.microservice.student.entities.Student;
import com.microservice.student.persistence.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private com.microservice.student.client.CourseClient courseClient;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return studentRepository.findAllByCourseId(courseId);
    }

    @Override
    public List<StudentByCourseResponse> findAllWithCourses() {
        List<Student> students = (List<Student>) studentRepository.findAll();

        return students.stream().map(student -> {
            CourseDTO course = null;
            try {
                course = courseClient.findCourseById(student.getCourseId());
            } catch (Exception e) {
                System.err.println("Error al obtener curso: " + e.getMessage());
            }

            return StudentByCourseResponse.builder()
                    .studentName(student.getName())
                    .studentLastName(student.getLastName())
                    .studentEmail(student.getEmail())
                    .courseName(course != null ? course.getName() : "Sin curso")
                    .courseTeacher(course != null ? course.getTeacher() : "Sin profesor")
                    .build();
        }).collect(Collectors.toList());
    }


    @Override
    public StudentByCourseResponse findStudentWithCourse(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        CourseDTO course = null;
        try {
            course = courseClient.findCourseById(student.getCourseId());
        } catch (Exception e) {
            System.err.println("Error llamando a cursos: " + e.getMessage());
        }

        return StudentByCourseResponse.builder()
                .studentName(student.getName())
                .studentLastName(student.getLastName())
                .studentEmail(student.getEmail())
                .courseName(course != null ? course.getName() : "Sin curso")
                .courseTeacher(course != null ? course.getTeacher() : "Sin profesor")
                .build();
    }
}