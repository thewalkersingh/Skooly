package com.thewa.skooly.exception;
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String message) {
	  super(message);
   }
}