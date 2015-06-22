/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BooksIO {
	private final JAXBContext context;

	public BooksIO() throws JAXBException {
		context = JAXBContext.newInstance(Books.class);
	}

	public Books readFile(File file) throws JAXBException {
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Books books = (Books) unmarshaller.unmarshal(file);
		return books;
	}

	public void writeFile(Books books, File file) throws JAXBException {
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(books, file);
	}
}