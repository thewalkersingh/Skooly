package com.thewa.skooly.controller;

import com.thewa.skooly.dto.AuthRequest;
import com.thewa.skooly.dto.AuthResponse;
import com.thewa.skooly.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   
   private final AuthService authService;
   
   public AuthController(AuthService authService) {
	  this.authService = authService;
   }
   
   @PostMapping("/login")
   public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
	  return ResponseEntity.ok(authService.authenticate(authRequest));
   }
}