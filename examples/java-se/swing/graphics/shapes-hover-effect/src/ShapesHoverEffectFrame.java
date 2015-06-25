/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ShapesHoverEffectFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ShapesHoverEffectFrame() {
		super("Shapes Hover Effect - Java Example by andbin");

		//----------------------------------------------------------------------
		// Creates a green ellipse.
		//----------------------------------------------------------------------
		Ellipse2D.Float ellipse1 = new Ellipse2D.Float(30, 50, 200, 100);
		ShapeElement shapeElement1 = new ShapeElement(ellipse1, Color.GREEN,
				"A green ellipse");

		//----------------------------------------------------------------------
		// Creates a semi-transparent yellow rectangle.
		//----------------------------------------------------------------------
		Rectangle2D.Float rectangle1 = new Rectangle2D.Float(180, 100, 180, 70);
		ShapeElement shapeElement2 = new ShapeElement(rectangle1,
				new Color(255, 255, 0, 200), "A semi-transparent yellow rectangle");

		//----------------------------------------------------------------------
		// Creates a blue arc.
		//----------------------------------------------------------------------
		Arc2D.Float arc1 = new Arc2D.Float(30, 30, 100, 100, 0, 135, Arc2D.PIE);
		ShapeElement shapeElement3 = new ShapeElement(arc1, Color.BLUE, "A blue arc");

		//----------------------------------------------------------------------
		// Creates an orange irregular polygon with 5 sides.
		//----------------------------------------------------------------------
		Polygon polygon1 = new Polygon(
				new int[] {  80, 120, 100, 140,  30 },   // x points
				new int[] { 160, 175, 200, 240, 210 },   // y points
				5);   // number of points
		ShapeElement shapeElement4 = new ShapeElement(polygon1, Color.ORANGE,
				"An orange irregular polygon with 5 sides");

		//----------------------------------------------------------------------
		// Creates a red shape using two ellipses (circles) and combining
		// them with the exclusive-OR operation.
		//----------------------------------------------------------------------
		Area circleArea1 = new Area(new Ellipse2D.Float(220, 150, 80, 80));
		Area circleArea2 = new Area(new Ellipse2D.Float(260, 110, 80, 80));
		// circleArea1 is both source and destination of the operation.
		circleArea1.exclusiveOr(circleArea2);

		ShapeElement shapeElement5 = new ShapeElement(circleArea1, Color.RED,
				"A red shape formed by two cicles in exclusive-OR");

		//----------------------------------------------------------------------
		// Creates a gray rounded rectangle rotated by 30 degrees
		//----------------------------------------------------------------------

		// Creates a rounded rectangle centered on the origin (top-left) of the panel.
		Area roundRectArea1 = new Area(new RoundRectangle2D.Float(-40, -20, 80, 40, 20, 20));

		AffineTransform transform1 = new AffineTransform();   // Identity transformation.
		transform1.translate(360, 50);   // Translates the origin.
		transform1.rotate(Math.toRadians(30));   // Rotates around the new translated origin.

		roundRectArea1 = roundRectArea1.createTransformedArea(transform1);

		ShapeElement shapeElement6 = new ShapeElement(roundRectArea1, Color.GRAY,
				"A gray rounded rectangle rotated by 30 degrees");


		// Creates the ShapesHoverEffectPanel and adds the shape elements.
		ShapesHoverEffectPanel panel = new ShapesHoverEffectPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);

		panel.addShapeElement(shapeElement1);
		panel.addShapeElement(shapeElement2);
		panel.addShapeElement(shapeElement3);
		panel.addShapeElement(shapeElement4);
		panel.addShapeElement(shapeElement5);
		panel.addShapeElement(shapeElement6);

		getContentPane().add(panel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 320);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}