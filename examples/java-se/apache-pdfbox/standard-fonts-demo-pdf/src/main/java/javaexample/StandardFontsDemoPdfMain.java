/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

public class StandardFontsDemoPdfMain {
	public static void main(String[] args) {
		String filename = "standard-fonts-demo.pdf";

		try {
			StandardFontsDemoPdf standardFontsDemoPdf = new StandardFontsDemoPdf(filename);

			System.out.format("Generating %s ... ", filename);
			standardFontsDemoPdf.generateDocument();
			System.out.println("Ok");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}