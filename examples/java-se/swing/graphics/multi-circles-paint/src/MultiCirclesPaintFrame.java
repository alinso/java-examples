/*
 * Copyright (C) 2016-2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MultiCirclesPaintFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private MultiCirclesPaintPanel multiCirclesPaintPanel;
	private JPanel controlPanel;
	private JButton addRandomCircleButton;
	private JButton removeAllCirclesButton;

	public MultiCirclesPaintFrame() {
		super("Multi Circles Paint - Java Example by andbin");

		// Creates the main paint panel for circles.
		multiCirclesPaintPanel = new MultiCirclesPaintPanel();
		multiCirclesPaintPanel.setOpaque(true);
		multiCirclesPaintPanel.setBackground(Color.WHITE);

		// Creates the control panel.
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		addRandomCircleButton = new JButton("Add random circle");
		removeAllCirclesButton = new JButton("Remove all circles");

		// Layouts components into the control panel.
		controlPanel.add(addRandomCircleButton);
		controlPanel.add(Box.createHorizontalStrut(10));   // a space of 10 pixels
		controlPanel.add(removeAllCirclesButton);

		// Layouts components into the content pane of this frame.
		add(multiCirclesPaintPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);

		// Setups the action for buttons using "anonymous inner classes".
		addRandomCircleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRandomCircle();
			}
		});

		removeAllCirclesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				multiCirclesPaintPanel.removeAllCircleElements();
			}
		});

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}


	private void addRandomCircle() {
		// Generates random values for center x/y and radius.
		int centerX = (int) (Math.random() * multiCirclesPaintPanel.getWidth());
		int centerY = (int) (Math.random() * multiCirclesPaintPanel.getHeight());
		int radius = (int) (Math.random() * 40 + 10);

		// Calculates a random color using the HSB color model.
		float hue = (float) Math.random();
		float saturation = 1.0f;   // maximum saturation
		float brightness = (float) (Math.random() * 0.5 + 0.5);

		Color fillColor = Color.getHSBColor(hue, saturation, brightness);

		// Creates and adds the new CircleElement.
		CircleElement circleElement = new CircleElement(centerX, centerY, radius, fillColor);
		multiCirclesPaintPanel.addCircleElement(circleElement);
	}
}