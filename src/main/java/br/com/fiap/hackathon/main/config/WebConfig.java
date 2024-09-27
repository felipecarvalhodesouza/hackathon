package br.com.fiap.hackathon.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable).
					authorizeHttpRequests(request -> {
						request.requestMatchers("/compromissos")
							.hasAuthority("MEDICO")
						.requestMatchers("/home")
							.hasAuthority("PACIENTE")
							.requestMatchers("/cadastro", "/cadastro/paciente", "/cadastro/medico", "/login", "/error", "/css/**", "/js/**")
							.permitAll()
						
						.anyRequest().authenticated();
					})
					.formLogin(formLogin -> formLogin.loginPage("/login").permitAll());
				return httpSecurity.build();
	}
}
