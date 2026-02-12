package com.data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        
	        registry.addResourceHandler("/images/**")
//	                .addResourceLocations("\"C:\\multi-vendor-marketplace-system\\backend\\uploads\\stores\"");
	        .addResourceLocations(
	                "file:C:/multi-vendor-marketplace-system/backend/uploads/stores/"
	            );
	    }
}
