/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public class ScalableSmileyPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		// Calls the original paintComponent in JPanel before custom paintings.
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Activates the "anti-aliasing" feature for a good quality.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Gets the physical width/height of the panel.
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// Calculates the center of the panel (also center of the smiley).
		float centerX = panelWidth / 2.0f;
		float centerY = panelHeight / 2.0f;

		// Calculates the "box" of the smiley (minimum between width and height).
		int boxSize = Math.min(panelWidth, panelHeight);

		if (boxSize > 2) {
			// Subtracts 2 so that the smiley doesn't really touch the borders.
			boxSize -= 2;
		}

		//----------------------------------------------------------------------
		// Smiley face
		//----------------------------------------------------------------------
		float borderWidth = boxSize * 0.04f;
		float circleSize = boxSize - borderWidth;
		float faceSize = boxSize - borderWidth * 2;

		// Calculates the top-left coordinates of the circle.
		float circleX = centerX - circleSize / 2;
		float circleY = centerY - circleSize / 2;

		Ellipse2D.Float faceCircle = new Ellipse2D.Float(circleX, circleY, circleSize, circleSize);

		// Fills the smiley face with the yellow color.
		g2d.setColor(Color.YELLOW);
		g2d.fill(faceCircle);

		// Strokes the outline of the smiley face with the black color.
		Stroke outlineStroke = new BasicStroke(borderWidth);
		g2d.setStroke(outlineStroke);
		g2d.setColor(Color.BLACK);
		g2d.draw(faceCircle);

		//----------------------------------------------------------------------
		// Smiley eyes
		//----------------------------------------------------------------------
		float eyeWidth = faceSize * 0.11f;     // Eye width
		float eyeHeight = faceSize * 0.18f;    // Eye height
		float eyeOffsetX = faceSize * 0.15f;   // X offset from the center
		float eyeOffsetY = faceSize * 0.18f;   // Y offset from the center

		float eye1X = centerX - eyeOffsetX - eyeWidth / 2;
		float eye2X = centerX + eyeOffsetX - eyeWidth / 2;
		float eyeY = centerY - eyeOffsetY - eyeHeight / 2;

		Ellipse2D.Float eye1 = new Ellipse2D.Float(eye1X, eyeY, eyeWidth, eyeHeight);
		Ellipse2D.Float eye2 = new Ellipse2D.Float(eye2X, eyeY, eyeWidth, eyeHeight);

		// Fills the eyes of the smiley face with the black color.
		g2d.setColor(Color.BLACK);
		g2d.fill(eye1);
		g2d.fill(eye2);

		//----------------------------------------------------------------------
		// Smiley mouth
		//----------------------------------------------------------------------
		float mouthX1 = centerX - faceSize * 0.35f;   // X of left edge of the mouth
		float mouthX2 = centerX + faceSize * 0.35f;   // X of right edge of the mouth
		float mouthY = centerY + faceSize * 0.1f;     // Y of left/right edge of the mouth
		float mouthY1 = centerY + faceSize * 0.40f;   // Y of quadratic control point 1
		float mouthY2 = centerY + faceSize * 0.56f;   // Y of quadratic control point 2

		GeneralPath mouthPath = new GeneralPath();
		mouthPath.moveTo(mouthX1, mouthY);
		mouthPath.quadTo(centerX, mouthY1, mouthX2, mouthY);
		mouthPath.quadTo(centerX, mouthY2, mouthX1, mouthY);

		// Fills the mouth of the smiley face with the black color.
		g2d.setColor(Color.BLACK);
		g2d.fill(mouthPath);
	}
}