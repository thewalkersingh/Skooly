package com.thewa.skooly.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "schools", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address", "location"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class School {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(nullable = false, unique = true)
   private String name = "Default School";
   
   @Column(nullable = false)
   private String address = "Default Address";
   
   @Column(nullable = false)
   private String location = "Default Location";
   
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private SchoolType type = SchoolType.CBSE;
   
   @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Student> students;
}