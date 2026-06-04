package com.microservice.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentByCourseResponse {
    private String studentName;
    private String studentLastName;
    private String studentEmail;
    private String courseName;
    private String courseTeacher;
}