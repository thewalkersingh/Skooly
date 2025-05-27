package com.thewa.skooly.mapper;
import com.thewa.skooly.dto.SchoolDTO;
import com.thewa.skooly.model.School;
import com.thewa.skooly.model.SchoolType;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {
   public SchoolDTO convertToDTO(School school) {
	  return SchoolDTO.builder()
					  .name(school.getName())
					  .address(school.getAddress())
					  .location(school.getLocation())
					  .type(school.getType())
					  .build();
   }
   
   public School convertToEntity(SchoolDTO schoolDTO) {
	  return School.builder()
				   .name(schoolDTO.getName() != null ? schoolDTO.getName() : "Test School")
				   .address(schoolDTO.getAddress() != null ? schoolDTO.getAddress() : "Default Address")
				   .location(schoolDTO.getLocation() != null ? schoolDTO.getLocation() : "Default Location")
				   .type(schoolDTO.getType() != null ? schoolDTO.getType() : SchoolType.CBSE)
				   .build();
   }
}