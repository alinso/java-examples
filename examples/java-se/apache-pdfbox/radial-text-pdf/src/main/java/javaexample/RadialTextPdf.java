/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

import static javaexample.util.MeasureUtils.degrees2Radians;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class RadialTextPdf {
	private String filename;
	private PDRectangle pageRect;

	public RadialTextPdf(String filename, PDRectangle pageRect) {
		this.filename = filename;
		this.pageRect = pageRect;
	}

	public String getFilename() {
		return filename;
	}

	public void generateDocument() throws IOException, COSVisitorException {
		PDDocument document = new PDDocument();

		try {
			// Sets some document metadata.
			PDDocumentInformation information = new PDDocumentInformation();
			information.setTitle("Radial Text PDF example with Apache PDFBox");
			information.setAuthor("Andrea Binello (\"andbin\")");
			document.setDocumentInformation(information);

			// Generates and saves the document.
			generatePage(document);
			document.save(filename);
		} finally {
			try {
				document.close();
			} catch (IOException e) {}
		}
	}

	private void generatePage(PDDocument document) throws IOException {
		// Creates a new page.
		PDPage page = new PDPage(pageRect);
		document.addPage(page);

		// Gets boundings of the page.
		PDRectangle rect = page.getMediaBox();

		// Calculates the side of the square that fits into the page.
		float squareSide = Math.min(rect.getWidth(), rect.getHeight());

		// Calculates the center point of the page.
		float centerX = (rect.getLowerLeftX() + rect.getUpperRightX()) / 2;
		float centerY = (rect.getLowerLeftY() + rect.getUpperRightY()) / 2;

		PDPageContentStream cos = new PDPageContentStream(document, page);

		// Creates the font for the radial text.
		PDFont font = PDType1Font.HELVETICA_BOLD;   // Standard font
		float fontSize = squareSide / 30;
		float fontAscent = font.getFontDescriptor().getAscent() / 1000 * fontSize;

		// Calculates key values for the drawings.
		float textX = squareSide / 3.4F;      // x of the text.
		float textY = -fontAscent / 2;        // y of the text (for vertical centering of text).
		float lineToX = textX * 0.97F;        // x destination for the line.
		float lineWidth = squareSide / 900;   // width of lines.

		// Moves the origin (0,0) of the axes to the center of the page.
		cos.concatenate2CTM(AffineTransform.getTranslateInstance(centerX, centerY));

		for (float degrees = 0; degrees < 360; degrees += 7.5) {
			double radians = degrees2Radians(degrees);

			// Creates a pure color with the hue based on the angle.
			Color textColor = Color.getHSBColor(degrees/360.0F, 1, 1);

			// Saves the graphics state because the angle changes on each iteration.
			cos.saveGraphicsState();

			// Rotates the axes by the angle expressed in radians.
			cos.concatenate2CTM(AffineTransform.getRotateInstance(radians));

			// Draws a line from the center of the page.
			cos.setLineWidth(lineWidth);
			cos.moveTo(0, 0);
			cos.lineTo(lineToX, 0);
			cos.stroke();

			// Draws the radial text.
			cos.beginText();
			cos.setNonStrokingColor(textColor);
			cos.setFont(font, fontSize);
			cos.moveTextPositionByAmount(textX, textY);
			cos.drawString("PDF");
			cos.endText();

			// Restores the graphics state to remove rotation transformation.
			cos.restoreGraphicsState();
		}

		cos.close();
	}
}