package org.reader.app.form;

import javax.validation.constraints.NotBlank;

import org.reader.app.model.GuestbookEntry;

public class GuestbookForm {

	private final @NotBlank String name;
	private final @NotBlank String text;

	
	public GuestbookForm(String name, String text) {

		this.name = name;
		this.text = text;
	}


	public String getName() {
		return name;
	}


	public String getText() {
		return text;
	}


	public GuestbookEntry toNewEntry() {
		return new GuestbookEntry(getName(), getText());
	}
}
