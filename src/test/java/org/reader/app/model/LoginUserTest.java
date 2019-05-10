package org.reader.app.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class LoginUserTest {
	LoginUser admin = new LoginUser("admin","admin");
	LoginUser guest = new LoginUser("conroy","guest1");
	
	@Test
	public void validUserTest() {
		String expected ="admin";
		assertEquals(expected,admin.getUsername());
		assertThat(admin.getPassword().equals(admin.getUsername()));
	}
	
	@Test
	public void invalidUserTest(){
		assertNotEquals(guest.getUsername(),equals(admin.getUsername()));
	}

}
