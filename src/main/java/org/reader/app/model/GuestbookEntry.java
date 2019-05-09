package org.reader.app.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.Assert;



@Entity
public class GuestbookEntry {

	private @Id 
	@GeneratedValue 
	Long id;
	private String name;
	private String text;
	private LocalDateTime date;
	
	public GuestbookEntry () {
		
	}
	
	public GuestbookEntry(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public GuestbookEntry(GuestbookEntryBuilder builder) {

		setName(builder.name);
		setText(builder.text);
		setDate(builder.date);
	}

	public static class GuestbookEntryBuilder{
		 @Id @GeneratedValue Long id;
		 String name, text;
		 LocalDateTime date;
		 
		 public GuestbookEntryBuilder name(String name) {
			 this.name = name;
			 return this;
		 }
		 
		 public GuestbookEntryBuilder text(String text) {
			 this.text = text;
			 return this;
		 }
		 	
		 public GuestbookEntryBuilder date() {
			 this.date = LocalDateTime.now();
			 return this;
		 }
		 
		 public GuestbookEntry build() {
			 GuestbookEntry entity = new GuestbookEntry(this);
			 entity.name = name;
			 entity.text = text;
			 entity.date = date;
				return  entity;
			}
	}
	
	
	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "GuestbookEntry [id=" + id + ", name=" + name + ", text=" + text + ", date=" + date + "]";
	}
	
}
