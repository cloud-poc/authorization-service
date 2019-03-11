package org.akj.springboot.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
				"/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/springfox-swagger-ui/**");
	}

	/**
	 * configure authentication
	 *
	 * @param authenticationManagerBuilder
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("irving")
//                .password(passwordEncoder().encode("123456"))
//                .roles("read");

		// auth.userDetailsService(userDetailsService())
		// .passwordEncoder(passwordEncoder());

		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		authenticationManagerBuilder.parentAuthenticationManager(authenticationManagerBean());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable();

		// http.authorizeRequests().antMatchers("/oauth/**",
		// "/login").permitAll().anyRequest().authenticated().and() //
		// .formLogin().loginPage("/login").permitAll().and().logout().permitAll();

		// 在 UsernamePasswordAuthenticationFilter 前添加 BeforeLoginFilter
		// http.addFilterBefore(new BeforeLoginFilter(),
		// UsernamePasswordAuthenticationFilter.class);

		http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers("/oauth/**").permitAll();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}