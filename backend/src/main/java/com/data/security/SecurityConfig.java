package com.data.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.data.repository.UserRepository;

@Configuration
public class SecurityConfig {
	
	
	@Autowired 
	private UserRepository userRepo;
	
	
	
	
	 @Bean
	 public UserDetailsService userDetailsService() {
	        return email -> {
	            return userRepo.findByEmail(email)
	                    .map(user -> org.springframework.security.core.userdetails.User
	                            .withUsername(user.getEmail())
	                            .password(user.getPassword())
	                            .roles(user.getRole().name()) // ADMIN / CUSTOMER / VENDOR
	                            .build())
	                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        };
	  }
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil,
                                       UserDetailsService userDetailsService) {
        return new JwtAuthFilter(jwtUtil, userDetailsService);
    }
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();

	    config.setAllowedOrigins(List.of("http://localhost:5173"));
	    config.setAllowedMethods(
	        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
	    );
	    config.setAllowedHeaders(List.of("*"));
	    config.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    return source;
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthFilter jwtAuthFilter) throws Exception {

        http
        	.cors(Customizer.withDefaults())
        	.csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            		 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/auth/login","/api/auth/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/store/stores","/api/product/store/**").permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/user/**").hasRole("CUSTOMER")
                    .requestMatchers("/api/vendor/**").hasRole("VENDOR")
                    .anyRequest().authenticated()
            )
      .addFilterBefore(jwtAuthFilter,
                   UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
	

}
