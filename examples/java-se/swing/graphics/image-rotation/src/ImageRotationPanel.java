/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class ImageRotationPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image image;
	private double degreesAngle;
	private double radiansAngle;
	private Object interpolationHintValue;

	public ImageRotationPanel(Image image) {
		this.image = image;
	}

	public double getDegreesAngle() {
		return degreesAngle;
	}

	public void setDegreesAngle(double degreesAngle) {
		// Updates and repaints only if the degreesAngle is really changed.
		if (this.degreesAngle != degreesAngle) {
			this.degreesAngle = degreesAngle;
			radiansAngle = Math.toRadians(degreesAngle);
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	public Object getInterpolationHintValue() {
		return interpolationHintValue;
	}

	public void setInterpolationHintValue(Object interpolationHintValue) {
		// Updates and repaints only if the interpolationHintValue is really changed.
		if (this.interpolationHintValue != interpolationHintValue) {
			this.interpolationHintValue = interpolationHintValue;
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Gets the physical width/height of the panel.
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// Gets the width/height of the image.
		//
		// NOTE: since the image in this example is loaded asynchronously using
		// the AWT Toolkit, it is possible that the width/height is not immediately
		// known. In this case -1 is returned and the image "observer" (the 'this')
		// is notified later when there are more informations on the image.
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);

		// Tests for valid image width/height.
		if (imageWidth != -1 && imageHeight != -1) {
			// Saves the original transformation matrix.
			AffineTransform origTransform = g2d.getTransform();

			// Translates the origin (0,0) to the center of the panel.
			g2d.translate(panelWidth/2, panelHeight/2);

			// Rotates to the specific angle.
			g2d.rotate(radiansAngle);

			// Sets the interpolation hint value, if present.
			if (interpolationHintValue != null) {
				g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolationHintValue);
			}

			// Draws the image centered on the origin (the center of the panel).
			g2d.drawImage(image, -imageWidth/2, -imageHeight/2, this);

			// Restores the original transformation matrix.
			g2d.setTransform(origTransform);

			FontMetrics fm = g2d.getFontMetrics();
			// Activates the text "anti-aliasing" feature for a good quality.
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			// Draws the angle information.
			g.drawString(degreesAngle + "\u00B0", 10, 10+fm.getAscent());
		}
	}
}