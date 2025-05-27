package com.thewa.skooly.service;
import com.thewa.skooly.dto.SchoolDTO;
import com.thewa.skooly.exception.ResourceNotFoundException;
import com.thewa.skooly.mapper.SchoolMapper;
import com.thewa.skooly.model.School;
import com.thewa.skooly.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
   private final SchoolRepository schoolRepository;
   private final SchoolMapper schoolMapper;
   
   public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
	  this.schoolRepository = schoolRepository;
	  this.schoolMapper = schoolMapper;
   }
   
   public List<SchoolDTO> getAllSchools() {
	  return schoolRepository.findAll().stream()
							 .map(schoolMapper::convertToDTO)
							 .toList();
   }
   
   public SchoolDTO getSchoolById(Long id) {
	  School school = schoolRepository
			  .findById(id)
			  .orElseThrow(() -> new ResourceNotFoundException("School with ID " + id + " not found"));
	  return schoolMapper.convertToDTO(school);
   }
   
   public SchoolDTO getSchoolByName(String name) {
	  School school = schoolRepository
			  .findByName(name)
			  .orElseThrow(() -> new ResourceNotFoundException("School with name " + name + " not found"));
	  return schoolMapper.convertToDTO(school);
   }
   
   public SchoolDTO saveSchool(SchoolDTO schoolDTO) {
	  School school = schoolMapper.convertToEntity(schoolDTO);
	  School savedSchool = schoolRepository.save(school);
	  return schoolMapper.convertToDTO(savedSchool);
   }
   
   public void deleteSchool(Long id) {
	  if(!schoolRepository.existsById(id)){
		 throw new ResourceNotFoundException("School with ID " + id + " not found");
	  }
	  schoolRepository.deleteById(id);
   }
}