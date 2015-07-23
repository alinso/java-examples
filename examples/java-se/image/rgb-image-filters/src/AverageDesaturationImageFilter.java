/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.image.RGBImageFilter;

public class AverageDesaturationImageFilter extends RGBImageFilter {
	public AverageDesaturationImageFilter() {
		// Sets the 'canFilterIndexColorModel' variable in RGBImageFilter class.
		// A "true" means that this filter does *not* depend on pixel's location
		// (x/y) and thus the filter can also be applied to images with an
		// IndexColorModel (with a color table like GIF images).
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		int alpha = rgb & 0xFF000000;   // Keeps original alpha.

		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8) & 0xFF;
		int b = rgb & 0xFF;

		// Calculates gray level as the average of Red/Green/Blue.
		int gray = (r + g + b) / 3;

		rgb = gray << 16 | gray << 8 | gray;

		return rgb | alpha;   // Returns RGB with original alpha.
	}
}