/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.image.RGBImageFilter;

public class ColorMaskImageFilter extends RGBImageFilter {
	public static final int ALPHA_MASK = 0xFF000000;
	public static final int RED_MASK   = 0x00FF0000;
	public static final int GREEN_MASK = 0x0000FF00;
	public static final int BLUE_MASK  = 0x000000FF;

	private final int rgbMask;

	public ColorMaskImageFilter(int rgbMask) {
		this.rgbMask = rgbMask;

		// Sets the 'canFilterIndexColorModel' variable in RGBImageFilter class.
		// A "true" means that this filter does *not* depend on pixel's location
		// (x/y) and thus the filter can also be applied to images with an
		// IndexColorModel (with a color table like GIF images).
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		return rgb & rgbMask;   // Returns the masked color.
	}

	// Factory methods to create ColorMaskImageFilter for common cases.

	public static ColorMaskImageFilter getRedPass() {
		return new ColorMaskImageFilter(RED_MASK | ALPHA_MASK);
	}

	public static ColorMaskImageFilter getGreenPass() {
		return new ColorMaskImageFilter(GREEN_MASK | ALPHA_MASK);
	}

	public static ColorMaskImageFilter getBluePass() {
		return new ColorMaskImageFilter(BLUE_MASK | ALPHA_MASK);
	}
}