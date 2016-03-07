/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

import static javaexample.util.MeasureUtils.mm2pt;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class StandardFontsDemoPdf {
	private static final float[] DEMO_FONT_SIZES = { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	private static final String DEMO_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final Color NULL_COLOR = new Color(128, 0, 0);
	private static final Color NUMBER_COLOR = new Color(0, 0, 205);
	private static final Color STRING_COLOR = new Color(64, 64, 255);
	private static final Color BOOLEAN_COLOR = new Color(0, 148, 0);
	private static final Color OTHER_COLOR = Color.BLACK;

	private String filename;
	private PDDocument document;
	private PDFont demoFont;
	private PDFontDescriptor demoFontDescriptor;
	private PDPage page;
	private PDPageContentStream cos;
	private float x;
	private float y;

	public StandardFontsDemoPdf(String filename) {
		this.filename = filename;
	}

	public void generateDocument() throws IOException, COSVisitorException {
		document = new PDDocument();

		try {
			// Sets some document metadata.
			PDDocumentInformation information = new PDDocumentInformation();
			information.setTitle("Standard fonts demo PDF example with Apache PDFBox");
			information.setAuthor("Andrea Binello (\"andbin\")");
			document.setDocumentInformation(information);

			// Generates and saves the document.
			generatePages();
			document.save(filename);
		} finally {
			try {
				document.close();
			} catch (IOException e) {}
		}
	}

	private void generatePages() throws IOException {
		String[] standardFontNames = PDType1Font.getStandard14Names();

		// Sorts the array of names because they are not in any specific order.
		Arrays.sort(standardFontNames);

		for (String standardFontName : standardFontNames) {
			// Generates a page for each font.
			demoFont = PDType1Font.getStandardFont(standardFontName);
			demoFontDescriptor = demoFont.getFontDescriptor();
			generatePage();
		}
	}

	private void generatePage() throws IOException {
		// Creates a new page.
		page = new PDPage(PDPage.PAGE_SIZE_A4);
		document.addPage(page);

		// Starting coordinates.
		x = mm2pt(15);
		y = mm2pt(276);

		cos = new PDPageContentStream(document, page);

		// Draws font name as page title.
		cos.setFont(PDType1Font.HELVETICA, 24);
		drawText(demoFont.getBaseFont(), x, y);

		y -= mm2pt(10);

		// Draws all font features.
		drawFontFeature("allCap"         , demoFontDescriptor.isAllCap());
		drawFontFeature("ascent"         , demoFontDescriptor.getAscent());
		drawFontFeature("averageWidth"   , demoFontDescriptor.getAverageWidth());
		drawFontFeature("capHeight"      , demoFontDescriptor.getCapHeight());
		drawFontFeature("charSet"        , demoFontDescriptor.getCharSet());
		drawFontFeature("descent"        , demoFontDescriptor.getDescent());
		drawFontFeature("fixedPitch"     , demoFontDescriptor.isFixedPitch());
		drawFontFeature("flags"          , demoFontDescriptor.getFlags());
		drawFontFeature("fontBoundingBox", demoFontDescriptor.getFontBoundingBox());
		drawFontFeature("fontFamily"     , demoFontDescriptor.getFontFamily());
		drawFontFeature("fontName"       , demoFontDescriptor.getFontName());
		drawFontFeature("fontStretch"    , demoFontDescriptor.getFontStretch());
		drawFontFeature("fontWeight"     , demoFontDescriptor.getFontWeight());
		drawFontFeature("forceBold"      , demoFontDescriptor.isForceBold());
		drawFontFeature("italic"         , demoFontDescriptor.isItalic());
		drawFontFeature("italicAngle"    , demoFontDescriptor.getItalicAngle());
		drawFontFeature("leading"        , demoFontDescriptor.getLeading());
		drawFontFeature("maxWidth"       , demoFontDescriptor.getMaxWidth());
		drawFontFeature("missingWidth"   , demoFontDescriptor.getMissingWidth());
		drawFontFeature("nonSymbolic"    , demoFontDescriptor.isNonSymbolic());
		drawFontFeature("script"         , demoFontDescriptor.isScript());
		drawFontFeature("serif"          , demoFontDescriptor.isSerif());
		drawFontFeature("smallCap"       , demoFontDescriptor.isSmallCap());
		drawFontFeature("stemH"          , demoFontDescriptor.getStemH());
		drawFontFeature("stemV"          , demoFontDescriptor.getStemV());
		drawFontFeature("symbolic"       , demoFontDescriptor.isSymbolic());
		drawFontFeature("xHeight"        , demoFontDescriptor.getXHeight());

		y -= mm2pt(5);
		cos.setNonStrokingColor(Color.BLACK);

		// Draws font demo string in different sizes.
		for (int i = 0; i < DEMO_FONT_SIZES.length; i++) {
			float demoFontSize = DEMO_FONT_SIZES[i];

			y -= demoFontSize + 9;

			cos.setFont(PDType1Font.HELVETICA, 8);
			drawText(demoFontSize + " pt", x, y);   // Draws points size.

			cos.setFont(demoFont, demoFontSize);
			drawText(DEMO_STRING, x + mm2pt(12), y);   // Draws demo string.
		}

		cos.close();
	}

	private void drawFontFeature(String featureName, Object featureValue) throws IOException {
		// Draws feature name.
		cos.setFont(PDType1Font.COURIER_BOLD, 8);
		cos.setNonStrokingColor(Color.BLACK);
		drawText(featureName, x, y);

		cos.setFont(PDType1Font.COURIER, 8);
		drawText("=", x + mm2pt(28), y);

		// Detects feature value type to set text and color.
		String text;
		Color color;

		if (featureValue == null) {
			text = "null";
			color = NULL_COLOR;
		} else if (featureValue instanceof Number) {
			text = String.valueOf(featureValue);
			color = NUMBER_COLOR;
		} else if (featureValue instanceof String) {
			text = "\"" + featureValue + "\"";
			color = STRING_COLOR;
		} else if (featureValue instanceof Boolean) {
			text = String.valueOf(featureValue);
			color = BOOLEAN_COLOR;
		} else {
			text = String.valueOf(featureValue);
			color = OTHER_COLOR;
		}

		// Draws feature value.
		cos.setNonStrokingColor(color);
		drawText(text, x + mm2pt(31), y);

		y -= 12;
	}

	private void drawText(String text, float x, float y) throws IOException {
		cos.beginText();
		cos.moveTextPositionByAmount(x, y);
		cos.drawString(text);
		cos.endText();
	}
}