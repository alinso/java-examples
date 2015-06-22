/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// The XmlRootElement annotation is required to define the "root" element-class.
@XmlRootElement
public class Books {
	private List<Book> booksList;

	// A "no-arg" constructor is required by JAXB.
	public Books() {}

	// Convenience constructor.
	public Books(List<Book> booksList) {
		this.booksList = booksList;
	}

	// If you don't use the XmlElement annotation with name "book", you would have
	// <booksList> elements (from "booksList" property derived by get/setBooksList).
	@XmlElement(name = "book")
	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}
}