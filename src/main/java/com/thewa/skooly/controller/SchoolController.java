package com.thewa.skooly.controller;
import com.thewa.skooly.dto.SchoolDTO;
import com.thewa.skooly.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
   private final SchoolService schoolService;
   
   public SchoolController(SchoolService schoolService) {
	  this.schoolService = schoolService;
   }
   
   @GetMapping
   public ResponseEntity<List<SchoolDTO>> getAllSchools() {
	  return ResponseEntity.ok(schoolService.getAllSchools());
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<SchoolDTO> getSchoolById(@PathVariable Long id) {
	  return ResponseEntity.ok(schoolService.getSchoolById(id));
   }
   
   @GetMapping("/name/{name}")
   public ResponseEntity<SchoolDTO> getSchoolByName(@PathVariable String name) {
	  return ResponseEntity.ok(schoolService.getSchoolByName(name));
   }
   
   @PostMapping
   public ResponseEntity<SchoolDTO> addSchool(@RequestBody SchoolDTO schoolDTO) {
	  return ResponseEntity.ok(schoolService.saveSchool(schoolDTO));
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
	  schoolService.deleteSchool(id);
	  return ResponseEntity.noContent().build();
   }
}