/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public class CustomBackgroundsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private TiledImagePanel tiledImagePanel;
	private AlphaColorPanel alphaColorPanel;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private List<AbstractButton> buttons = new ArrayList<AbstractButton>();

	public CustomBackgroundsFrame() {
		super("Custom Backgrounds - Java Example by andbin");

		tiledImagePanel = new TiledImagePanel();
		setContentPane(tiledImagePanel);

		// Sets the layout of the content pane (that is the TiledImagePanel).
		setLayout(new GridBagLayout());

		// Creates a panel for the buttons. This panel is an AlphaColorPanel and
		// uses a semi-transparent color.
		alphaColorPanel = new AlphaColorPanel();
		alphaColorPanel.setLayout(new GridLayout(0, 2, 10, 10));
		alphaColorPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		alphaColorPanel.setBackground(new Color(255, 255, 255, 148));   // Semi-transparent white.

		setupBackgroundImage("blue007.jpg");
		setupBackgroundImage("lgrey047.jpg");
		setupBackgroundImage("orang080.jpg");
		setupBackgroundImage("rock065.jpg");
		setupBackgroundImage("Set no background", null);

		buttons.get(0).doClick();   // Performs a "click" action on the first button.

		getContentPane().add(alphaColorPanel, new GridBagConstraints());

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void setupBackgroundImage(String imageName) {
		try {
			BufferedImage bufImg = loadImage(imageName);
			setupBackgroundImage("Set " + imageName, bufImg);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"IMAGE LOADING ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void setupBackgroundImage(String label, BufferedImage bufImg) {
		JToggleButton button = new JToggleButton(label);
		button.addActionListener(new ActionListenerImpl(bufImg));

		alphaColorPanel.add(button);
		buttonGroup.add(button);
		buttons.add(button);
	}

	private void applyBackgroundImage(BufferedImage bufImg) {
		tiledImagePanel.setTileImage(bufImg);
	}

	private BufferedImage loadImage(String imageName) throws IOException {
		String resourceName = "images/" + imageName;

		// The image file is searched using the "resource" concept. See the
		// documentation of getResource/getResourceAsStream in java.lang.Class.
		URL imageUrl = getClass().getResource(resourceName);

		if (imageUrl == null) {
			throw new IOException("Resource " + resourceName + " not found");
		}

		BufferedImage bufImg = ImageIO.read(imageUrl);
		return bufImg;
	}


	private class ActionListenerImpl implements ActionListener {
		private BufferedImage bufImg;

		public ActionListenerImpl(BufferedImage bufImg) {
			this.bufImg = bufImg;
		}

		public void actionPerformed(ActionEvent e) {
			applyBackgroundImage(bufImg);
		}
	}
}