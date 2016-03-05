/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample.util;

public class MeasureUtils {
	private MeasureUtils() {}

	public static float mm2pt(float millimeters) {
		return millimeters * 72 / 25.4F;
	}

	public static float mm2pt(double millimeters) {
		return (float) (millimeters * 72 / 25.4F);
	}

	public static float pt2mm(float points) {
		return points * 25.4F / 72;
	}

	public static float pt2mm(double points) {
		return (float) (points * 25.4F / 72);
	}

	public static double degrees2Radians(double degrees) {
		return degrees * Math.PI / 180;
	}

	public static double radians2Degrees(double radians) {
		return radians * 180 / Math.PI;
	}
}