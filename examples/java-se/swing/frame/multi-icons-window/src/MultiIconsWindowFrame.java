/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MultiIconsWindowFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String[] appIconResourceNames = {
		"icons/java-icon-16.png",
		"icons/java-icon-32.png",
		"icons/java-icon-48.png",
		"icons/java-icon-64.png",
		"icons/java-icon-96.png",
		"icons/java-icon-128.png"
	};

	public MultiIconsWindowFrame() {
		super("Multi Icons Window - Java Example by andbin");

		List<Image> iconsList = new ArrayList<Image>();

		for (String appIconResourceName : appIconResourceNames) {
			iconsList.add(loadImage(appIconResourceName));
		}

		// Sets the icons list to the frame.
		setIconImages(iconsList);

		// Shows the icons also in the frame, just for demonstration.
		JPanel demoPanel = new JPanel();
		demoPanel.setLayout(new BoxLayout(demoPanel, BoxLayout.PAGE_AXIS));
		demoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (String appIconResourceName : appIconResourceNames) {
			Image iconImage = loadImage(appIconResourceName);
			JLabel demoLabel = new JLabel(appIconResourceName);
			demoLabel.setIcon(new ImageIcon(iconImage));
			demoLabel.setIconTextGap(20);
			demoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 100));
			demoPanel.add(demoLabel);
		}

		getContentPane().add(demoPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private Image loadImage(String resourceName) {
		// The image file is searched using the "resource" concept. See the
		// documentation of getResource/getResourceAsStream in java.lang.Class.
		URL imageUrl = getClass().getResource(resourceName);
		return Toolkit.getDefaultToolkit().getImage(imageUrl);
	}
}