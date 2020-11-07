package com.student.studentdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.studentdemo.exception.StudentNotFoundException;
import com.student.studentdemo.model.Student;
import com.student.studentdemo.repository.StudentRepository;

@RestController
@RequestMapping("/CollegeNWM")
public class StudentController {

	@Autowired
	private StudentRepository studentrepository;

// Get all
	@GetMapping("/students")
	public List<Student> getallStudents() {
		return studentrepository.findAll();
	}

// create
	@PostMapping("/students")
	public Student createStudent(@Valid @RequestBody Student std) {
		return studentrepository.save(std);
	}

// get by id1
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Integer studentId)
			throws StudentNotFoundException {
		Student std = studentrepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student details not found for: " + studentId));
		return ResponseEntity.ok().body(std);
	}

	// update
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
			@RequestBody Student studentDetails) throws StudentNotFoundException {
		Student std = studentrepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student details not found for: " + studentId));
		std.setFirstName(studentDetails.getFirstName());
		std.setLastName(studentDetails.getLastName());
		std.setEmailId(studentDetails.getEmailId());
		studentrepository.save(std);
		return ResponseEntity.ok().body(std);
	}

// delete
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") Integer studentId)
			throws StudentNotFoundException {
		studentrepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student details not found for: " + studentId));

		studentrepository.deleteById(studentId);
		return ResponseEntity.ok().build();

	}

 

}
