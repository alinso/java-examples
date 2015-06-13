/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FontResources {
	// Private constructor to avoid instantiation.
	private FontResources() {}

	public static Font loadFont(String name, float fontSize)
			throws FontFormatException, IOException {
		String resourceName = "fonts/" + name;

		// The font file is searched using the "resource" concept. See the
		// documentation of getResource/getResourceAsStream in java.lang.Class.
		InputStream is = FontResources.class.getResourceAsStream(resourceName);

		if (is == null) {
			throw new FileNotFoundException("Font resource '" + resourceName + "' not found.");
		}

		Font font;

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} finally {
			// The close is required because the javadoc of createFont clearly
			// states that: "This method does not close the InputStream".
			is.close();
		}

		// The font is loaded with initial size 1, so it is necessary to derive
		// the font with a more appropriate/useful size.
		return font.deriveFont(fontSize);
	}
}