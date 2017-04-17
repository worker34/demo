package com.example.entity;

import javax.validation.constraints.*;

public class Journal {

	@Min(value=0)
	@Max(value=100)
	private int id;

	@NotNull(message = "Title of the journal not maybe null ")
	@Size(min=4, max=30, message="Title of journal must be between 4 and 30 characters long.")
	private String title;

	@NotNull(message = "Author of the journal not maybe null ")
	@Size(min=4, max=20, message="Invalid author name.")
	private String author;

	@Min(value=1, message="The journal are many pages.")
	@Max(value=2000, message="The journal are many pages.")
	private int pages;

	@NotNull(message = "Publisher of the journal not maybe null ")
	@Size(min=4, max=20, message="Invalid publisher name.")
	private String publisher;

	@Min(value=1980, message="Invalid year.")
	@Max(value=2017, message="Invalid year.")
	private int year;

	private String issn;
	private String description;

	@Min(value=1, message="Check price.")
	@Max(value=1980, message="Check price.")
	private int price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
