/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksIOWithJAXB {
	public static void main(String[] args) {
		try {
			BooksIO booksIO = new BooksIO();

			Books books = booksIO.readFile(new File("books-input.xml"));

			// Creates a new Book with two authors. The use of Arrays.asList
			// for the authors is just only for convenience.
			Book book2 = new Book("Java Generics and Collections", null, 286,
					"0-596-52775-6", "978-0-596-52775-4");

			book2.setAuthors(Arrays.asList(
					new Author("Maurice", "Naftalin"),
					new Author("Philip", "Wadler")
					));

			// Creates an ArrayList for the new books list. It is more safe
			// to create a new list because we cannot know for sure which List
			// implementation (and if mutable or immutable) is instantiated by
			// the JAXB implementation used.
			List<Book> booksList = new ArrayList<Book>();
			booksList.addAll(books.getBooksList());   // Adds original book(s).
			booksList.add(book2);

			books.setBooksList(booksList);

			booksIO.writeFile(books, new File("books-output.xml"));
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}