package com.skooly.backend.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Teacher {
   private int id;
   private String name;
   private int age;
}