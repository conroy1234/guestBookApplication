package org.reader.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig  extends WebSecurityConfigurerAdapter{
 
	@Override
    protected void configure(AuthenticationManagerBuilder aut) throws Exception {
    	
		UserBuilder users = User.withDefaultPasswordEncoder();
		aut.inMemoryAuthentication()
		.withUser(users.username("guest1").password("guest1").roles("GUESS"))
		.withUser(users.username("admin").password("admin").roles("ADMIN"));
    }
}
