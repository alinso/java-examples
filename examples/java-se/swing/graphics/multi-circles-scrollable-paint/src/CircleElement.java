/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;

/*
 * This is a simple "bean" class that contains informations about one single
 * circle element.
 */
public class CircleElement {
	private int centerX;
	private int centerY;
	private int radius;
	private Color fillColor;

	public CircleElement(int centerX, int centerY, int radius, Color fillColor) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.fillColor = fillColor;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


	// Custom methods to get other useful informations

	public int getLeftX() {
		return getCenterX() - getRadius();
	}

	public int getTopY() {
		return getCenterY() - getRadius();
	}

	public int getDiameter() {
		return getRadius() * 2;
	}
}