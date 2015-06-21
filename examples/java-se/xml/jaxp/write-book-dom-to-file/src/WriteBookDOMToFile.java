/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteBookDOMToFile {
	public static void main(String[] args) {
		try {
			Document bookDocument = createBookDocument();
			writeDocumentToFile(bookDocument, "book.xml");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static Document createBookDocument() throws ParserConfigurationException {
		// Please, read the documentation of this newInstance() to understand
		// the lookup procedure used by the framework to find the
		// DocumentBuilderFactory implementation.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		System.out.println("DocumentBuilderFactory implementation: " + factory.getClass());

		DocumentBuilder builder = factory.newDocumentBuilder();
		System.out.println("DocumentBuilder implementation: " + builder.getClass());

		// Creates a new document with a <book> as root element.
		Document document = builder.getDOMImplementation().createDocument(null, "book", null);
		Element bookRootElement = document.getDocumentElement();

		// Adds attributes to the <book> element.
		bookRootElement.setAttribute("title", "The Java Programming Language");
		bookRootElement.setAttribute("pages", "928");
		bookRootElement.setAttribute("edition", "4th");
		bookRootElement.setAttribute("isbn10", "0-321-34980-6");
		bookRootElement.setAttribute("isbn13", "978-0-321-34980-4");

		// Creates a <description> element with a text content.
		Element descriptionElement = document.createElement("description");
		descriptionElement.appendChild(document.createTextNode("The fourth edition "
				+ "of \"The Java Programming Language\" directly from the "
				+ "creators of the Java programming language."));
		bookRootElement.appendChild(descriptionElement);

		// Creates three <author> elements, one for each author.
		Element authorElement;

		authorElement = document.createElement("author");
		authorElement.setAttribute("firstName", "Ken");
		authorElement.setAttribute("lastName", "Arnold");
		bookRootElement.appendChild(authorElement);

		authorElement = document.createElement("author");
		authorElement.setAttribute("firstName", "James");
		authorElement.setAttribute("lastName", "Gosling");
		bookRootElement.appendChild(authorElement);

		authorElement = document.createElement("author");
		authorElement.setAttribute("firstName", "David");
		authorElement.setAttribute("lastName", "Holmes");
		bookRootElement.appendChild(authorElement);

		return document;
	}

	public static void writeDocumentToFile(Document document, String filename)
			throws TransformerException {
		// Please, read the documentation of this newInstance() to understand
		// the lookup procedure used by the framework to find the
		// TransformerFactory implementation.
		TransformerFactory factory = TransformerFactory.newInstance();
		System.out.println("TransformerFactory implementation: " + factory.getClass());

		Transformer transformer = factory.newTransformer();
		System.out.println("Transformer implementation: " + transformer.getClass());

		Source source = new DOMSource(document);
		Result result = new StreamResult(filename);

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
	}
}