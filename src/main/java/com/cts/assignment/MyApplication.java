package com.cts.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.cts.assignment.security.JwtFilter;

//@EnableCaching
@SpringBootApplication
@SpringBootConfiguration
public class MyApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MyApplication.class, args);
		
	}

	// @Bean
	// public FilterRegistrationBean jwtFilter() {
	// FilterRegistrationBean registrationBean = new FilterRegistrationBean();

	// registrationBean.setFilter(new JwtFilter());
	// registrationBean.addUrlPatterns("/user/*");
	// registrationBean.addUrlPatterns("/stock/*");

	// return registrationBean;
	// }
}
