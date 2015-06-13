/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class DigitalClockFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	// The "Digital dream" font is designed and copyrighted by Jakob Fischer / pizzadude.dk
	private static final String CLOCK_FONT_RESOURCE = "digital-dream-skew.ttf";

	private DigitalClockPanel digitalClockPanel;

	public DigitalClockFrame() throws Exception {
		super("Digital Clock - Java Example by andbin");

		// Loads the custom font for the clock.
		Font clockFont = loadFont(CLOCK_FONT_RESOURCE, 60);

		// Creates the digital clock panel and sets all the specific properties.
		digitalClockPanel = new DigitalClockPanel();
		digitalClockPanel.setClockBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		digitalClockPanel.setClockForeground(new Color(255, 224, 0));
		digitalClockPanel.setClockBackground(Color.BLACK);
		digitalClockPanel.setClockFont(clockFont);

		getContentPane().add(digitalClockPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private Font loadFont(String name, float fontSize) throws FontFormatException, IOException {
		try {
			return FontResources.loadFont(name, fontSize);
		} catch (FontFormatException e) {
			showErrorMessage(this, "Error loading font '" + name + "': invalid format.");
			throw e;
		} catch (FileNotFoundException e) {
			showErrorMessage(this, "Error loading font '" + name + "': resource not found.");
			throw e;
		} catch (IOException e) {
			showErrorMessage(this, "Error loading font '" + name + "': I/O error.");
			throw e;
		}
	}

	private void showErrorMessage(Component parentComponent, Object message) {
		JOptionPane.showMessageDialog(parentComponent, message,
				"ERROR", JOptionPane.ERROR_MESSAGE);
	}

}