package com.thewa.skooly.service;
import com.thewa.skooly.dto.AuthRequest;
import com.thewa.skooly.dto.AuthResponse;
import com.thewa.skooly.model.User;
import com.thewa.skooly.repository.UserRepository;
import com.thewa.skooly.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtUtil jwtUtil; // Inject JwtUtil correctly
   
   public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
	  this.userRepository = userRepository;
	  this.passwordEncoder = passwordEncoder;
	  this.jwtUtil = jwtUtil;
   }
   
   public AuthResponse authenticate(AuthRequest authRequest) {
	  User user = userRepository.findByUsername(authRequest.getUsername())
								.orElseThrow(() -> new RuntimeException("User not found"));
	  
	  if(!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())){
		 throw new RuntimeException("Invalid credentials");
	  }
	  
	  String token = jwtUtil.generateJwtToken(user.getUsername(), user.getRole().name()); // Updated method call
	  return new AuthResponse(token);
   }
   public AuthResponse refreshToken(String expiredToken) {
	  String username = jwtUtil.extractUsername(expiredToken);
	  String role = jwtUtil.extractRole(expiredToken);
	  
	  String newToken = jwtUtil.generateJwtToken(username, role);
	  return new AuthResponse(newToken);
   }
   
}