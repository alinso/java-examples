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
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImagesAlphaBlendingFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String IMAGE1_RESOURCE = "images/orange.jpg";
	private static final String IMAGE2_RESOURCE = "images/lime.jpg";

	private ImagesAlphaBlendingPanel imagesAlphaBlendingPanel;
	private JSlider alphaSlider;

	public ImagesAlphaBlendingFrame() {
		super("Images Alpha Blending - Java Example by andbin");

		Image image1 = loadImage(IMAGE1_RESOURCE);
		Image image2 = loadImage(IMAGE2_RESOURCE);

		// Creates thumbnails maintaining the aspect ratio of the original image.
		Image image1Thumbnail = image1.getScaledInstance(64, -1, Image.SCALE_SMOOTH);
		Image image2Thumbnail = image2.getScaledInstance(64, -1, Image.SCALE_SMOOTH);

		imagesAlphaBlendingPanel = new ImagesAlphaBlendingPanel(image1, image2);
		imagesAlphaBlendingPanel.setBackground(new Color(160, 160, 160));
		imagesAlphaBlendingPanel.setOpaque(true);
		imagesAlphaBlendingPanel.setPreferredSize(new Dimension(550, 400));

		// Creates a slider with range [0, 100] and initial value of 0.
		alphaSlider = new JSlider(0, 100, 0);
		alphaSlider.createStandardLabels(10);
		alphaSlider.setMajorTickSpacing(10);
		alphaSlider.setMinorTickSpacing(2);
		alphaSlider.setPaintLabels(true);
		alphaSlider.setPaintTicks(true);
		alphaSlider.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		controlPanel.add(new JLabel(new ImageIcon(image1Thumbnail)), BorderLayout.LINE_START);
		controlPanel.add(alphaSlider, BorderLayout.CENTER);
		controlPanel.add(new JLabel(new ImageIcon(image2Thumbnail)), BorderLayout.LINE_END);

		getContentPane().add(imagesAlphaBlendingPanel, BorderLayout.CENTER);
		getContentPane().add(controlPanel, BorderLayout.SOUTH);

		// Registers a change listener implemented as anonymous inner class.
		alphaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeAlpha();
			}
		});

		// Sets a different initial value to cause a change event.
		alphaSlider.setValue(50);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void changeAlpha() {
		int percentAlphaValue = alphaSlider.getValue();

		// Floating-point division to get the alpha value in range [0.0, 1.0].
		float alphaValue = percentAlphaValue / 100.0f;

		imagesAlphaBlendingPanel.setAlphaValue(alphaValue);
	}

	private Image loadImage(String resourceName) {
		// The image file is searched using the "resource" concept. See the
		// documentation of getResource/getResourceAsStream in java.lang.Class.
		URL imageUrl = getClass().getResource(resourceName);
		return Toolkit.getDefaultToolkit().getImage(imageUrl);
	}
}