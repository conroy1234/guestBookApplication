package org.reader.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
public class LoginUser {

	@Id
	private  String username;
	private  String password;
	
	
	
	
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + "]";
	}
	
	
	
	

}
