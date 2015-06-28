/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

// This class is a custom implementation of the Icon interface. The realized
// icon is a simple colored "box" (square/rectangle) with a border of 1 pixel.

public class ColorSwatchIcon implements Icon {
	public static final Color DEFAULT_BORDER_COLOR = new Color(108, 108, 108);   // A medium/dark gray.

	private int width;
	private int height;
	private Color color;
	private Color borderColor;

	public ColorSwatchIcon(int width, int height, Color color) {
		this(width, height, color, DEFAULT_BORDER_COLOR);
	}

	public ColorSwatchIcon(int width, int height, Color color, Color borderColor) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.borderColor = borderColor;
	}

	public Color getColor() {
		return color;
	}

	// Implementation of the paintIcon method to fulfill the Icon interface.
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// Draws the 1 pixel wide border.
		g.setColor(borderColor);
		g.drawRect(x, y, width-1, height-1);

		// Fills the interior of the icon.
		g.setColor(color);
		g.fillRect(x+1, y+1, width-2, height-2);
	}

	// Implementation of the getIconWidth method to fulfill the Icon interface.
	public int getIconWidth() {
		return width;
	}

	// Implementation of the getIconHeight method to fulfill the Icon interface.
	public int getIconHeight() {
		return height;
	}
}