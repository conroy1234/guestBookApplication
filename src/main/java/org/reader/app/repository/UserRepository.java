package org.reader.app.repository;

import org.reader.app.model.LoginUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<LoginUser, String> {
	
public LoginUser findByUsername(String username);
public LoginUser findByPassword(String username);

}
