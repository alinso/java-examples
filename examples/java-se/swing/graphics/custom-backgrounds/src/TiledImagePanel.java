/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class TiledImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage tileImage;
	private BufferedImage compatibleTileImage;

	public TiledImagePanel() {
		setOpaque(true);

		// Sets a BorderLayout so that this panel is initially similar to the
		// content pane of JFrame/JDialog/etc...
		setLayout(new BorderLayout());
	}

	public void setTileImage(BufferedImage tileImage) {
		if (this.tileImage != tileImage) {
			this.tileImage = tileImage;
			// Forces the generation of a new compatible image the next time
			// it is necessary (see in paintComponent).
			compatibleTileImage = null;
			// Asks a repaint for the entire panel.
			repaint();
		}
	}

	protected void paintComponent(Graphics g) {
		if (tileImage == null) {
			// If there is no tile image, calls the original paintComponent.
			super.paintComponent(g);
		} else {
			ensureCompatibleImage();

			int panelWidth = getWidth();
			int panelHeight = getHeight();

			int imageWidth = compatibleTileImage.getWidth();
			int imageHeight = compatibleTileImage.getHeight();

			// Fills the entire surface repeating the tile image in both directions.
			for (int y = 0; y < panelHeight; y += imageHeight) {
				for (int x = 0; x < panelWidth; x += imageWidth) {
					g.drawImage(compatibleTileImage, x, y, null);
				}
			}
		}
	}

	private void ensureCompatibleImage() {
		if (compatibleTileImage == null) {
			int width = tileImage.getWidth();
			int height = tileImage.getHeight();

			// Gets the GraphicsConfiguration of this panel.
			GraphicsConfiguration gc = getGraphicsConfiguration();

			// A "compatible" image has a pixel data format that best suits
			// the format used by the display device (in this case the display
			// where this panel is visible). Thus a compatible image is faster
			// to copy onto the display device.
			compatibleTileImage = gc.createCompatibleImage(width, height);

			// Copies the image from the original BufferedImage to the
			// "compatible" BufferedImage.
			Graphics g = compatibleTileImage.getGraphics();
			g.drawImage(tileImage, 0, 0, null);
			g.dispose();
		}
	}
}