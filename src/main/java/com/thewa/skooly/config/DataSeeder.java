package com.thewa.skooly.config;
import com.thewa.skooly.model.*;
import com.thewa.skooly.repository.SchoolRepository;
import com.thewa.skooly.repository.StudentRepository;
import com.thewa.skooly.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
   private final SchoolRepository schoolRepository;
   private final StudentRepository studentRepository;
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   
   public DataSeeder(SchoolRepository schoolRepository, StudentRepository studentRepository, UserRepository userRepository,
		   PasswordEncoder passwordEncoder) {
	  this.schoolRepository = schoolRepository;
	  this.studentRepository = studentRepository;
	  this.userRepository = userRepository;
	  this.passwordEncoder = passwordEncoder;
   }
   
   @Override
   public void run(String... args) {
	  if(userRepository.count() == 0){
		 List<User> users = List.of(
				 new User(null, "admin", passwordEncoder.encode("admin123"), Role.ADMIN),
				 new User(null, "student1", passwordEncoder.encode("student123"), Role.STUDENT));
		 
		 userRepository.saveAll(users);
		 System.out.println("Dummy user data loaded!");
	  }
	  if(schoolRepository.count() == 0){ // Prevent duplicate entries
		 List<School> schools = List.of(
				 new School(null, "Greenwood High", "123 Oak St", "Mumbai", SchoolType.CBSE, null),
				 new School(null, "St. Xavier's Academy", "456 Pine St", "Delhi", SchoolType.ICSE, null),
				 new School(null, "Delhi Public School", "789 Maple St", "Bangalore", SchoolType.STATE_BOARD, null)
									   );
		 schoolRepository.saveAll(schools);
		 System.out.println("Dummy school data loaded!");
	  }
	  
	  if(studentRepository.count() == 0){
		 List<School> schools = schoolRepository.findAll(); // Fetch saved schools
		 List<Student> students = List.of(
				 new Student(null, "STU001", "Amit Sharma", "2005-03-15", "10th Grade", schools.get(0)),
				 new Student(null, "STU002", "Priya Desai", "2006-06-22", "9th Grade", schools.get(1)),
				 new Student(null, "STU003", "Rahul Mehta", "2005-09-10", "10th Grade", schools.get(2)),
				 new Student(null, "STU004", "Sneha Kapoor", "2007-01-30", "8th Grade", schools.get(0)),
				 new Student(null, "STU005", "Arjun Nair", "2004-12-12", "12th Grade", schools.get(1)),
				 new Student(null, "STU006", "Megha Joshi", "2006-07-08", "9th Grade", schools.get(2)),
				 new Student(null, "STU007", "Vikram Singh", "2003-04-25", "12th Grade", schools.get(0)),
				 new Student(null, "STU008", "Anjali Reddy", "2007-11-18", "8th Grade", schools.get(1)),
				 new Student(null, "STU009", "Rajesh Kumar", "2005-05-07", "10th Grade", schools.get(2)),
				 new Student(null, "STU010", "Neha Verma", "2004-09-01", "11th Grade", schools.get(0))
										 );
		 studentRepository.saveAll(students);
		 System.out.println("Dummy student data loaded!");
	  }
   }
}