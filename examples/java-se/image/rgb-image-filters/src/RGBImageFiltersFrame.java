/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class RGBImageFiltersFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel imagesPanel;

	public RGBImageFiltersFrame(int rows, int cols, int hgap, int vgap) {
		super("RGB Image Filters - Java Example by andbin");

		imagesPanel = new JPanel(new GridLayout(rows, cols, hgap, vgap));
		imagesPanel.setBorder(BorderFactory.createEmptyBorder(vgap, hgap, vgap, hgap));
		imagesPanel.setOpaque(true);
		imagesPanel.setBackground(Color.WHITE);

		getContentPane().add(imagesPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void addImageBox(Image image, String title) {
		JLabel label = new JLabel(new ImageIcon(image));
		label.setText(title);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.TOP);

		imagesPanel.add(label);
	}
}