package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Journal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Title of the journal not maybe null")
	@Size(min=4, max=30, message="Title of journal must be between 4 and 30 characters long.")
	@Column(name = "title")
	private String title;

	@NotNull(message = "Author of the journal not maybe null ")
	@Size(min=4, max=20, message="Invalid author name.")
	@Column(name = "author")
	private String author;

	@Min(value=1, message="The journal are many pages.")
	@Max(value=2000, message="The journal are many pages.")
	@Column(name = "pages")
	private int pages;

	@NotNull(message = "Publisher of the journal not maybe null ")
	@Size(min=4, max=20, message="Invalid publisher name.")
	@Column(name = "publicher")
	private String publisher;

	@Min(value=1980, message="Invalid year.")
	@Max(value=2017, message="Invalid year.")
	@Column(name = "year")
	private int year;

	@Column(name = "issn")
	private String issn;

	@Column(name = "description")
	private String description;

	@Min(value=1, message="Check price.")
	@Max(value=1980, message="Check price.")
	private int price;
}
