/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagesAlphaBlendingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image image1;
	private Image image2;
	private float alphaValue;   // Alpha value in range [0.0, 1.0]
	private AlphaComposite alphaComposite1;
	private AlphaComposite alphaComposite2;

	public ImagesAlphaBlendingPanel(Image image1, Image image2) {
		this.image1 = image1;
		this.image2 = image2;
		setupAlpha(0);
	}

	public float getAlphaValue() {
		return alphaValue;
	}

	public void setAlphaValue(float alphaValue) {
		// Updates and repaints only if the alphaValue is really changed.
		if (this.alphaValue != alphaValue) {
			setupAlpha(alphaValue);
		}
	}

	private void setupAlpha(float alphaValue) {
		if (alphaValue < 0) {
			alphaValue = 0;
		} else if (alphaValue > 1) {
			alphaValue = 1;
		}

		this.alphaValue = alphaValue;
		alphaComposite1 = createAlphaComposite(1 - alphaValue);
		alphaComposite2 = createAlphaComposite(alphaValue);
		repaint();   // Asks a repaint for the entire panel.
	}

	// Overrides paintComponent for custom painting in this panel.
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		drawImageWithAlphaComposite(g2d, image1, alphaComposite1);
		drawImageWithAlphaComposite(g2d, image2, alphaComposite2);
	}

	private void drawImageWithAlphaComposite(Graphics2D g2d, Image image, AlphaComposite alphaComposite) {
		// Gets the width/height of the image.
		//
		// NOTE: since the images in this example are loaded asynchronously using
		// the AWT Toolkit, it is possible that the width/height is not immediately
		// known. In this case -1 is returned and the image "observer" (the 'this')
		// is notified later when there are more informations on the image.
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);

		if (imageWidth != -1 && imageHeight != -1) {
			// Gets the physical width/height of the panel.
			int panelWidth = getWidth();
			int panelHeight = getHeight();

			// Calculates the position of the image centered on the panel.
			int x = (panelWidth - imageWidth) / 2;
			int y = (panelHeight - imageHeight) / 2;

			// Saves the original Composite.
			Composite oldComposite = g2d.getComposite();

			g2d.setComposite(alphaComposite);
			g2d.drawImage(image, x, y, this);

			// Restores the original Composite.
			g2d.setComposite(oldComposite);
		}
	}

	private static AlphaComposite createAlphaComposite(float alpha) {
		return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	}
}