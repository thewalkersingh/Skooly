package com.thewa.skooly.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
   private String studentCode;
   private String fullName;
   private String dob;
   private String grade;
   private String board;
   private String schoolName;
   private Long schoolId;
}