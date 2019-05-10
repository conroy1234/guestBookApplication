package org.reader.app.config;

import org.reader.app.model.LoginUser;
import org.reader.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig  extends WebSecurityConfigurerAdapter{
 
	@Autowired
	UserRepository userRepository;
	
	
	LoginUser admin = new LoginUser("admin", "admin");
	LoginUser guess = new LoginUser("guest1", "guest1");
	
	@Override
    protected void configure(AuthenticationManagerBuilder aut) throws Exception {
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		aut.inMemoryAuthentication()
		.withUser(users.username(guess.getUsername()).password(guess.getPassword()).roles("GUESS"))
		.withUser(users.username(admin.getUsername()).password(admin.getPassword()).roles("ADMIN"));
    }
		
}
