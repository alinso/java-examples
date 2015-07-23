/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.image.RGBImageFilter;

public class NegativeImageFilter extends RGBImageFilter {
	public NegativeImageFilter() {
		// Sets the 'canFilterIndexColorModel' variable in RGBImageFilter class.
		// A "true" means that this filter does *not* depend on pixel's location
		// (x/y) and thus the filter can also be applied to images with an
		// IndexColorModel (with a color table like GIF images).
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		return rgb ^ 0x00FFFFFF;   // Negates bits in Red/Green/Blue components.
	}
}