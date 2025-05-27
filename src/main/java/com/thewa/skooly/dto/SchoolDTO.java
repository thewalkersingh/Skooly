package com.thewa.skooly.dto;
import com.thewa.skooly.model.SchoolType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolDTO {
   private String name;
   private String address;
   private String location;
   private Long schoolId;
   private SchoolType type;
}