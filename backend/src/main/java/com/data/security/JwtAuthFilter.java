package com.data.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	  @Autowired
	    private final JwtUtil jwtUtil;
	  	private final UserDetailsService userDetailsService;

	  	public JwtAuthFilter(JwtUtil jwtUtil,
                UserDetailsService userDetailsService) {
	  		this.jwtUtil = jwtUtil;
	  		this.userDetailsService = userDetailsService;
	  	}

	
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/login");
    }

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String header = request.getHeader("Authorization");
//		
//		if(header!=null && header.startsWith("Bearer ")) {
//			 String token = header.substring(7);
//			 String username = jwt.extractToken(token);
//			 
//			 if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//				 UserDetails userDetails = service.loadUserByUsername(username);
//				 
//				 if(jwt.validateToken(token)) {
//					 
//					 UsernamePasswordAuthenticationToken auth =
//							 new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
//					 
//					 SecurityContextHolder.getContext().setAuthentication(auth);
//				 }
//			 }
//		}
//		
//		filterChain.doFilter(request, response);
//	}

	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
