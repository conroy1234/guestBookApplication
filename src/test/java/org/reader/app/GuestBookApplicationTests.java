package org.reader.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reader.app.GuestBookApplication;
import org.reader.app.model.GuestbookEntry;
import org.reader.app.model.GuestbookEntry.GuestbookEntryBuilder;
import org.reader.app.repository.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GuestBookApplication.class)
@WebAppConfiguration
public class GuestBookApplicationTests {

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	GuestbookRepository guestBookRepository;
	
	MockMvc mockMvc;
	
	GuestbookEntry entry;
	List<GuestbookEntry> entries;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		 entry = guestBookRepository.save(new GuestbookEntryBuilder().name("Conroy White").text("visiting HR department").date().build());
		 entries =(List<GuestbookEntry>) guestBookRepository.findAll();
	}
	
	
	@Test
	public void persistsGuestbookEntry() {
		int expected = 4;			
		assertEquals(expected,entries.size());
	}
	
	@Test 
	public void findsGuestbookEntryByAuthorName() {		
		assertThat(guestBookRepository.findByName("Conroy White", Sort.by("date"))).doesNotContainNull();
	}
	
	@Test
	public void contextLoads() throws Exception {
		
		mockMvc.perform(get("/guestbook"))
		.andExpect(status().isOk())
		.andExpect(view().name("guestbook"))
		.andExpect(model().attributeExists("entries"))
		.andExpect(model().size(3));
	}
}

