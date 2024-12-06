package com.ticketing.tool.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ticketing.tool.auth.JwtAuthenticationEntryPoint;
import com.ticketing.tool.auth.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http

				.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorizeHttpRequests) -> {
					authorizeHttpRequests.requestMatchers("/api/v1/auth/**").permitAll();
				})

				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/v1/saveTicket", "/api/v1/getAllTickets", "/api/v1/next-id")
						.hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN", "ADMIN", "CONSULTANT", "APPROVER", "QUE_MANAGER",
								"USER"))

				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/approver", "/api/v1/approver/**")
						.hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN", "ADMIN", "CONSULTANT", "APPROVER", "QUE_MANAGER"))

				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/company/saveCompany", "/api/department/**", "api/company/**",
								"/api/approver-level/**")
						.hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN", "ADMIN", "CONSULTANT", "QUE_MANAGER"))

				.authorizeHttpRequests((authorizeHttpRequests) -> {
					authorizeHttpRequests.anyRequest().authenticated();
				}).exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
					httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint);
				})

				.sessionManagement((sessionManagement) -> sessionManagement.sessionConcurrency(
						(sessionConcurrency) -> sessionConcurrency.maximumSessions(1).expiredUrl("/login?expired")))
				.authenticationProvider(authenticationProvider)

				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
