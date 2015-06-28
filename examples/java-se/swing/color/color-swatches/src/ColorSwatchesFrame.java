/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class ColorSwatchesFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private ColorSwatchesPanel colorSwatchesPanel;
	private JLabel testLabel;

	public ColorSwatchesFrame() {
		super("Color Swatches - Java Example by andbin");

		colorSwatchesPanel = new ColorSwatchesPanel(ColorUtils.getWebSafeColors(), 18);

		// Creates a JPanel (with default FlowLayout) to keep the ColorSwatchesPanel
		// at its "preferred size".
		JPanel controlPanel = new JPanel();
		controlPanel.add(colorSwatchesPanel);

		// Creates a JLabel to test the choosen color.
		testLabel = new JLabel("The Java Programming Language");
		testLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testLabel.setFont(new Font("SansSerif", Font.BOLD, 40));

		Border outsideBorder = BorderFactory.createTitledBorder("COLOR TEST");
		Border insideBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border testBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
		testLabel.setBorder(testBorder);

		getContentPane().add(controlPanel, BorderLayout.NORTH);
		getContentPane().add(testLabel, BorderLayout.CENTER);

		// Registers a ColorListener implemented as anonymous inner class
		// to receive changes of the Color from the ColorSwatchesPanel object.
		colorSwatchesPanel.addColorListener(new ColorListener() {
			public void colorChanged(ColorChangeEvent e) {
				Color changedColor = e.getColor();
				testLabel.setForeground(changedColor);
			}
		});

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}