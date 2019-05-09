package org.reader.app.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.reader.app.model.GuestbookEntry.GuestbookEntryBuilder;


public class GuestbookEntryUnitTests {


	@Test
	void setsCreationDate() {
		
		assertThat(new GuestbookEntryBuilder().name("Conroy White").text("visiting HR department").date().build()).isNotNull();
	}
}
