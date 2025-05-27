package com.thewa.skooly.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  http.authorizeHttpRequests(authorize -> authorize
				  .requestMatchers("/h2-console/**").permitAll()
				  .anyRequest().authenticated())
		  .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
		  .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
	  return http.build();
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
   }
}