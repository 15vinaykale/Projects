package com.student.studentdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.studentdemo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
