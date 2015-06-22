/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// The XmlType annotation with propOrder attribute is used to declare a specific
// ordering of the properties. All properties must be listed, not just some.
@XmlType(propOrder = {"title", "edition", "isbn10", "isbn13", "pages", "authors"})
public class Book {
	private String title;
	private String edition;
	private int pages;
	private String isbn10;
	private String isbn13;
	private List<Author> authors;

	// A "no-arg" constructor is required by JAXB.
	public Book() {}

	// Convenience constructor.
	public Book(String title, String edition, int pages, String isbn10, String isbn13) {
		this.title = title;
		this.edition = edition;
		this.pages = pages;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
	}

	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlAttribute
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@XmlAttribute
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@XmlAttribute
	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	@XmlAttribute
	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	// If you don't use the XmlElement annotation with name "author", you would have
	// <authors> elements (from "authors" property derived by get/setAuthors).
	@XmlElement(name = "author")
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}