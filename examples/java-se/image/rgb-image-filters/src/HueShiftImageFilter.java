/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class HueShiftImageFilter extends RGBImageFilter {
	private final float hueShift;
	private float[] hsbValues = new float[3];

	public HueShiftImageFilter(float hueShift) {
		this.hueShift = hueShift;

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

		Color.RGBtoHSB(r, g, b, hsbValues);

		float hue = hsbValues[0] + hueShift;
		float saturation = hsbValues[1];
		float brightness = hsbValues[2];

		rgb = Color.HSBtoRGB(hue, saturation, brightness);
		rgb = rgb & 0x00FFFFFF;   // Clears alpha set by HSBtoRGB.

		return rgb | alpha;   // Returns RGB with original alpha.
	}
}