package com.example.productinventorysystem.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
			.authorizeRequests()
			.requestMatchers("/product/**").hasRole("USER")
			.requestMatchers("/category/**").hasRole("USER")
			.requestMatchers("/supplier/**").hasRole("USER")
			.anyRequest().authenticated()
			.and().httpBasic();
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetails() {
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user"))
				.roles("USER").build();
		
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user,admin);
	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}