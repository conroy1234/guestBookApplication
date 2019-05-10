package org.reader.app;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reader.app.model.GuestbookEntry.GuestbookEntryBuilder;
import org.reader.app.model.LoginUser;

import org.reader.app.repository.GuestbookRepository;
import org.reader.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuestBookApplication implements CommandLineRunner {

	@Autowired
	GuestbookRepository guestbookRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(GuestBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createUser();
		Stream.of(new GuestbookEntryBuilder().name("Conroy White").text("visiting HR department").date().build(),
				new GuestbookEntryBuilder().name("Stephen Wander").text("visiting HR A and R").date().build(),
				new GuestbookEntryBuilder().name("Billy Piper").text("visiting HR Tom Dailey").date().build()

		).forEach(guestBook -> {
			guestbookRepository.save(guestBook);
		});

	}

	public void createUser() {
		Stream.of(new LoginUser("guest1", "guest1"), new LoginUser("admin", "admin")).forEach(user -> {			
			userRepository.save(user);
			
			
		});

	}

}
