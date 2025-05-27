package com.thewa.skooly.mapper;
import com.thewa.skooly.dto.StudentDTO;
import com.thewa.skooly.model.School;
import com.thewa.skooly.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
   public StudentDTO convertToDTO(Student student) {
	  return StudentDTO.builder()
					   .studentCode(student.getStudentCode())
					   .fullName(student.getFullName())
					   .dob(student.getDob())
					   .grade(student.getGrade())
					   .schoolId(student.getSchool().getId())
					   .schoolName(student.getSchool().getName())
					   .build();
   }
   
   public Student convertToEntity(StudentDTO studentDTO, School school) {
	  return Student.builder()
					.studentCode(studentDTO.getStudentCode())
					.fullName(studentDTO.getFullName())
					.dob(studentDTO.getDob())
					.grade(studentDTO.getGrade())
					.school(school)
					.build();
   }
}