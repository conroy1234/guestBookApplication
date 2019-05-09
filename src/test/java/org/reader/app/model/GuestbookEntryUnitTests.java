package org.reader.app.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.reader.app.model.GuestbookEntry.GuestbookEntryBuilder;


public class GuestbookEntryUnitTests {


	@Test
	public void setsCreationDate() {
		GuestbookEntry entry = new GuestbookEntryBuilder().name("Conroy White").text("visiting HR department").date().build();
		assertEquals("Conroy White",entry.getName());
		assertThat(entry).isNotNull();
	}
}
