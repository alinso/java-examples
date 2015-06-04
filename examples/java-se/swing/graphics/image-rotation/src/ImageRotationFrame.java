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
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageRotationFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel interpolationHintLabel;
	private JComboBox interpolationHintComboBox;
	private ImageRotationPanel imageRotationPanel;
	private JSlider degreesAngleSlider;

	public ImageRotationFrame() {
		super("Image Rotation - Java Example by andbin");

		// Finds the image using the "resource" concept.
		URL imageUrl = getClass().getResource("photo.png");
		// Loads asynchronously the image using the AWT Toolkit.
		Image image = Toolkit.getDefaultToolkit().getImage(imageUrl);

		Object[] interpolationHintValues = {
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC,
		};

		interpolationHintLabel = new JLabel("Interpolation hint");
		interpolationHintComboBox = new JComboBox(interpolationHintValues);

		imageRotationPanel = new ImageRotationPanel(image);
		imageRotationPanel.setPreferredSize(new Dimension(520, 520));
		imageRotationPanel.setOpaque(true);
		imageRotationPanel.setBackground(new Color(211, 211, 211));   // A light gray
		imageRotationPanel.setFont(new Font("SansSerif", Font.PLAIN, 20));

		degreesAngleSlider = new JSlider(0, 360, 0);
		degreesAngleSlider.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		degreesAngleSlider.setMajorTickSpacing(90);
		degreesAngleSlider.setMinorTickSpacing(10);
		degreesAngleSlider.setPaintLabels(true);
		degreesAngleSlider.setPaintTicks(true);
		degreesAngleSlider.setToolTipText("Angle in degrees");

		JPanel interpolationHintPanel = new JPanel();
		interpolationHintPanel.setLayout(new BoxLayout(interpolationHintPanel, BoxLayout.LINE_AXIS));
		interpolationHintPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		interpolationHintPanel.add(interpolationHintLabel);
		interpolationHintPanel.add(Box.createHorizontalStrut(10));
		interpolationHintPanel.add(interpolationHintComboBox);

		getContentPane().add(interpolationHintPanel, BorderLayout.NORTH);
		getContentPane().add(imageRotationPanel, BorderLayout.CENTER);
		getContentPane().add(degreesAngleSlider, BorderLayout.SOUTH);

		// Registers an action listener (implemented as anonymous inner class)
		// to receive notification about the selection of the interpolation hint.
		interpolationHintComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object value = interpolationHintComboBox.getSelectedItem();
				imageRotationPanel.setInterpolationHintValue(value);
			}
		});

		// Registers a change listener (implemented as anonymous inner class)
		// to receive notification about the change of the angle.
		degreesAngleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				imageRotationPanel.setDegreesAngle(degreesAngleSlider.getValue());
			}
		});

		// Forces a change that fires an action event.
		interpolationHintComboBox.setSelectedItem(null);
		interpolationHintComboBox.setSelectedIndex(0);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}