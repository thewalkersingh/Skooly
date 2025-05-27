package com.thewa.skooly.service;
import com.thewa.skooly.dto.StudentDTO;
import com.thewa.skooly.exception.ResourceNotFoundException;
import com.thewa.skooly.mapper.StudentMapper;
import com.thewa.skooly.model.School;
import com.thewa.skooly.model.Student;
import com.thewa.skooly.repository.SchoolRepository;
import com.thewa.skooly.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
   private final StudentRepository studentRepository;
   private final StudentMapper studentMapper;
   private final SchoolRepository schoolRepository;
   
   public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
		   SchoolRepository schoolRepository) {
	  this.studentRepository = studentRepository;
	  this.studentMapper = studentMapper;
	  this.schoolRepository = schoolRepository;
   }
   
   public Page<StudentDTO> getAllStudents(Pageable pageable) {
	  return studentRepository.findAll(pageable).map(studentMapper::convertToDTO);
   }
   
   public StudentDTO getStudentById(Long id) {
	  Student student = studentRepository
			  .findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
	  return studentMapper.convertToDTO(student);
   }
   
   public StudentDTO getStudentByCode(String studentCode) {
	  Student student = Optional.ofNullable(studentRepository.findByStudentCode(studentCode))
								.orElseThrow(() -> new ResourceNotFoundException(
										"Student with code " + studentCode + " not found"));
	  return studentMapper.convertToDTO(student);
   }
   
   public StudentDTO saveStudent(StudentDTO studentDTO) {
	  School school = schoolRepository
			  .findById(studentDTO.getSchoolId()) // Use schoolId instead of studentCode
			  .orElseThrow(() -> new ResourceNotFoundException(
					  "School with ID " + studentDTO.getSchoolId() + " not found"));
	  Student student = studentMapper.convertToEntity(studentDTO, school);
	  Student savedStudent = studentRepository.save(student);
	  return studentMapper.convertToDTO(savedStudent);
   }
   
   public void deleteStudent(Long id) {
	  if(!studentRepository.existsById(id)){
		 throw new ResourceNotFoundException("Student with ID " + id + " not found");
	  }
	  studentRepository.deleteById(id);
   }
}