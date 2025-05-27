package com.thewa.skooly.repository;
import com.thewa.skooly.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
   Student findByStudentCode(String studentCode);
}