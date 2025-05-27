package com.thewa.skooly.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(nullable = false, unique = true)
   private String studentCode;
   
   @Column(nullable = false)
   private String fullName;
   
   @Column(nullable = false)
   private String dob;
   
   @Column(nullable = false)
   private String grade;
   
   @ManyToOne
   @JoinColumn(name = "school_id", nullable = false)
   private School school;
}