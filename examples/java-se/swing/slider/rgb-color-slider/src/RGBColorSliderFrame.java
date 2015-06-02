/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class RGBColorSliderFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private RGBColorSliderPanel rgbColorSliderPanel;
	private JPanel colorDemoPanel;

	public RGBColorSliderFrame() {
		super("RGB Color Slider - Java Example by andbin");

		rgbColorSliderPanel = new RGBColorSliderPanel();

		colorDemoPanel = new JPanel();
		colorDemoPanel.setOpaque(true);

		getContentPane().add(rgbColorSliderPanel, BorderLayout.NORTH);
		getContentPane().add(colorDemoPanel, BorderLayout.CENTER);

		// Registers a ColorListener implemented as anonymous inner class
		// to receive changes of the Color from the RGBColorSliderPanel object.
		rgbColorSliderPanel.addColorListener(new ColorListener() {
			public void colorChanged(ColorChangeEvent e) {
				Color color = e.getColor();
				colorDemoPanel.setBackground(color);
			}
		});

		// Sets an initial color value.
		rgbColorSliderPanel.setColor(Color.MAGENTA);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}