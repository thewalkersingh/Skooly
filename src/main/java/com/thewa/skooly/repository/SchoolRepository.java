package com.thewa.skooly.repository;
import com.thewa.skooly.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
   Optional<School> findByName(String name);
}