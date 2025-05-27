package com.thewa.skooly.controller;
import com.thewa.skooly.dto.StudentDTO;
import com.thewa.skooly.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
   private final StudentService studentService;
   
   public StudentController(StudentService studentService) {
	  this.studentService = studentService;
   }
   
   @GetMapping("/all")
   public ResponseEntity<Page<StudentDTO>> getAllStudents(Pageable pageable) {
	  return ResponseEntity.ok(studentService.getAllStudents(pageable));
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
	  return ResponseEntity.ok(studentService.getStudentById(id));
   }
   
   @GetMapping("/code/{studentCode}")
   public ResponseEntity<StudentDTO> getStudentByCode(@PathVariable String studentCode) {
	  return ResponseEntity.ok(studentService.getStudentByCode(studentCode));
   }
   
   @PostMapping("/save")
   public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
	  return ResponseEntity.ok(studentService.saveStudent(studentDTO));
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	  studentService.deleteStudent(id);
	  return ResponseEntity.noContent().build();
   }
}