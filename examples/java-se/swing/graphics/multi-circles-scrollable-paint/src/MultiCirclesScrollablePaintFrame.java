/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class MultiCirclesScrollablePaintFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private MultiCirclesScrollablePaintPanel paintPanel;
	private JScrollPane scrollPane;
	private JPanel controlPanel;
	private JButton addCircleButton;
	private JButton removeAllCirclesButton;
	private JLabel sizeInfoLabel;

	public MultiCirclesScrollablePaintFrame() {
		super("Multi Circles Scrollable Paint - Java Example by andbin");

		// Creates the components.
		paintPanel = new MultiCirclesScrollablePaintPanel();
		scrollPane = new JScrollPane(paintPanel);
		controlPanel = new JPanel();
		addCircleButton = new JButton("Add random circle");
		removeAllCirclesButton = new JButton("Remove all circles");
		sizeInfoLabel = new JLabel();

		// Setups the components.
		showPreferredSize();   // Shows initial size (which is 0x0)

		paintPanel.setOpaque(true);
		paintPanel.setBackground(Color.WHITE);

		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Layouts the components.
		controlPanel.add(addCircleButton);
		controlPanel.add(Box.createHorizontalStrut(10));   // a space of 10 pixels.
		controlPanel.add(removeAllCirclesButton);
		controlPanel.add(Box.createHorizontalStrut(10));   // a space of 10 pixels.
		controlPanel.add(sizeInfoLabel);

		add(scrollPane, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);

		// Setups the action for the buttons using anonymous inner classes.
		addCircleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRandomCircle();
			}
		});

		removeAllCirclesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAllCircles();
			}
		});

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(480, 340);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}


	private void addRandomCircle() {
		// Generates a random point/radius. Note that physical width/height
		// of the paintPanel are NOT used.
		int x = (int) (Math.random() * 1000);
		int y = (int) (Math.random() * 1000);
		int radius = (int) (Math.random() * 60 + 10);

		// Generates a random color from the HSB model.
		float hue = (float) Math.random();
		float saturation = 1.0f;
		float brightness = (float) (Math.random() * 0.5 + 0.5);

		Color fillColor = Color.getHSBColor(hue, saturation, brightness);

		// Creates and adds the new CircleElement.
		CircleElement circleElement = new CircleElement(x, y, radius, fillColor);
		paintPanel.addCircleElement(circleElement);
		showPreferredSize();
	}

	private void removeAllCircles() {
		paintPanel.removeAllCircleElement();
		showPreferredSize();
	}

	private void showPreferredSize() {
		Dimension dim = paintPanel.getPreferredSize();
		sizeInfoLabel.setText("Pref. size = " + dim.width + " x " + dim.height);
	}
}