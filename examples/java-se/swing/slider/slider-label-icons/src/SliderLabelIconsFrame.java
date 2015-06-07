/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderLabelIconsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private Icon[] smallIcons;
	private Icon[] largeIcons;
	private JSlider iconsSlider;
	private JLabel selectionLabel;

	public SliderLabelIconsFrame() {
		super("Slider Label Icons - Java Example by andbin");

		// Loads small/large icons.
		smallIcons = new Icon[3];
		smallIcons[0] = loadIcon("shield-green-small.png");
		smallIcons[1] = loadIcon("shield-yellow-small.png");
		smallIcons[2] = loadIcon("shield-red-small.png");

		largeIcons = new Icon[3];
		largeIcons[0] = loadIcon("shield-green-large.png");
		largeIcons[1] = loadIcon("shield-yellow-large.png");
		largeIcons[2] = loadIcon("shield-red-large.png");

		// Setups a slider for values in range [0, 2]
		iconsSlider = new JSlider(0, 2, 0);
		iconsSlider.setPaintTicks(true);
		iconsSlider.setMajorTickSpacing(1);
		iconsSlider.setMinorTickSpacing(1);
		iconsSlider.setSnapToTicks(true);
		iconsSlider.setPaintLabels(true);

		// Setups the label table for the slider.
		// Each entry is an association: Integer (0 to 2) --> JLabel
		Hashtable labels = new Hashtable();

		for (int i = 0; i < smallIcons.length; i++) {
			JLabel label = new JLabel(smallIcons[i]);
			labels.put(new Integer(i), label);
		}

		iconsSlider.setLabelTable(labels);

		// Sets a smaller width on the slider (default width is also good).
		Dimension d = iconsSlider.getPreferredSize();
		d.width = 141;
		iconsSlider.setPreferredSize(d);

		// Setups the label used to view the selected large icon.
		selectionLabel = new JLabel();
		selectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectionLabel.setOpaque(true);
		selectionLabel.setBackground(Color.WHITE);

		// Layouts components.
		JPanel sliderPanel = new JPanel(new FlowLayout());
		sliderPanel.add(iconsSlider);

		getContentPane().add(selectionLabel, BorderLayout.CENTER);
		getContentPane().add(sliderPanel, BorderLayout.SOUTH);

		// Registers a change listener implemented as anonymous inner classes.
		iconsSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = iconsSlider.getValue();
				selectionLabel.setIcon(largeIcons[index]);
			}
		});

		// Sets a different initial value to force a change event.
		iconsSlider.setValue(1);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(400, 340);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private ImageIcon loadIcon(String name) {
		// Finds the image icon using the "resource" concept.
		String resourceName = "icons/" + name;
		URL imageUrl = getClass().getResource(resourceName);

		if (imageUrl != null) {
			return new ImageIcon(imageUrl);
		} else {
			System.err.println("Resource " + resourceName + " not found!");
			return null;
		}
	}
}