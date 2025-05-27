package com.thewa.skooly.config;
import com.thewa.skooly.repository.UserRepository;
import com.thewa.skooly.security.CustomUserDetailsService;
import com.thewa.skooly.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
	  http.csrf(AbstractHttpConfigurer::disable)
		  .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
		  .authorizeHttpRequests(auth -> auth
				  .requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
				  .requestMatchers("/api/auth/login").hasRole("ADMIN")
				  .requestMatchers("/api/students/**").hasRole("STUDENT")
				  .anyRequest().authenticated())
		  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	  
	  return http.build();
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
   }
   
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	  return configuration.getAuthenticationManager();
   }
   
   @Bean
   public UserDetailsService userDetailsService(UserRepository userRepository) {
	  return new CustomUserDetailsService(userRepository);
   }
}