/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

import org.apache.pdfbox.pdmodel.PDPage;

public class RadialTextPdfMain {
	public static void main(String[] args) {
		RadialTextPdf[] radialTextPdfs = {
				new RadialTextPdf("radial-text-a3.pdf", PDPage.PAGE_SIZE_A3),
				new RadialTextPdf("radial-text-a4.pdf", PDPage.PAGE_SIZE_A4),
				new RadialTextPdf("radial-text-a5.pdf", PDPage.PAGE_SIZE_A5),
		};

		for (RadialTextPdf radialTextPdf : radialTextPdfs) {
			System.out.format("Generating %s ... ", radialTextPdf.getFilename());

			try {
				radialTextPdf.generateDocument();
				System.out.println("Ok");
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}