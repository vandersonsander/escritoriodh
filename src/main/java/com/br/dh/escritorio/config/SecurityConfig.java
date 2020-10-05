package com.br.dh.escritorio.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select email, senha, ativo"
					+ " from funcionario"
					+ " where email = ?")
			.authoritiesByUsernameQuery("select email, autoridade"
					+ " from autorizacao"
					+ " where email = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/pedido/**").hasAnyRole("FUNC", "GERENTE")
			.antMatchers("/produto/**").hasAnyRole("FUNC", "GERENTE")
			.antMatchers("/prod-pedido/**").hasAnyRole("FUNC", "GERENTE")
			.antMatchers("/cliente/**").hasAnyRole("FUNC", "GERENTE")
			.antMatchers("/endereco/**").hasAnyRole("FUNC", "GERENTE")
			.antMatchers("/telefone/**").hasAnyRole("FUNC", "GERENTE")
			.antMatchers("/ponto/**").hasAnyRole("FUNC", "GERENTE")//*/
			.antMatchers("/funcionario/**").hasAnyRole("GERENTE")
			.antMatchers("/gerente/**").hasAnyRole("GERENTE")
			.and().httpBasic();
			
	}
	
	
}
