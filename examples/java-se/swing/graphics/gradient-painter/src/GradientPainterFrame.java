/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

public class GradientPainterFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GradientPainterPanel gradientPainterPanel;
	private JMenuBar menuBar;
	private JMenu optionsMenu;
	private JMenuItem chooseFirstColorMenuItem;
	private JMenuItem chooseSecondColorMenuItem;
	private JRadioButtonMenuItem horizontalOrientationMenuItem;
	private JRadioButtonMenuItem verticalOrientationMenuItem;

	public GradientPainterFrame() {
		super("Gradient Painter - Java Example by andbin");

		gradientPainterPanel = new GradientPainterPanel(Color.RED, Color.GREEN);

		// Creates menu components.
		menuBar = new JMenuBar();
		optionsMenu = new JMenu("Options");
		chooseFirstColorMenuItem = new JMenuItem("Choose first color...");
		chooseSecondColorMenuItem = new JMenuItem("Choose second color...");
		horizontalOrientationMenuItem = new JRadioButtonMenuItem("Horizontal orientation");
		verticalOrientationMenuItem = new JRadioButtonMenuItem("Vertical orientation");

		// Creates a ButtonGroup for mutual exclusion of radio buttons.
		ButtonGroup orientationGroup = new ButtonGroup();
		orientationGroup.add(horizontalOrientationMenuItem);
		orientationGroup.add(verticalOrientationMenuItem);

		// Creates the menu structure.
		menuBar.add(optionsMenu);
		optionsMenu.add(chooseFirstColorMenuItem);
		optionsMenu.add(chooseSecondColorMenuItem);
		optionsMenu.addSeparator();
		optionsMenu.add(horizontalOrientationMenuItem);
		optionsMenu.add(verticalOrientationMenuItem);
		setJMenuBar(menuBar);

		getContentPane().add(gradientPainterPanel, BorderLayout.CENTER);

		// Creates and registers the listener.
		ListenerImpl listenerImpl = new ListenerImpl();

		chooseFirstColorMenuItem.addActionListener(listenerImpl);
		chooseSecondColorMenuItem.addActionListener(listenerImpl);
		horizontalOrientationMenuItem.addActionListener(listenerImpl);
		verticalOrientationMenuItem.addActionListener(listenerImpl);

		// Selects this item so that the horizontal orientation is the
		// predefined option (if you don't do this, both orientation options
		// are initially deselected!).
		horizontalOrientationMenuItem.setSelected(true);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 350);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void chooseFirstColor() {
		Color oldColor = gradientPainterPanel.getFirstColor();
		Color newColor = showChooseColorDialog("Select the first color", oldColor);

		if (newColor != null) {
			gradientPainterPanel.setFirstColor(newColor);
		}
	}

	private void chooseSecondColor() {
		Color oldColor = gradientPainterPanel.getSecondColor();
		Color newColor = showChooseColorDialog("Select the second color", oldColor);

		if (newColor != null) {
			gradientPainterPanel.setSecondColor(newColor);
		}
	}

	private void setHorizontalOrientation() {
		gradientPainterPanel.setOrientation(GradientOrientation.HORIZONTAL);
	}

	private void setVerticalOrientation() {
		gradientPainterPanel.setOrientation(GradientOrientation.VERTICAL);
	}

	private Color showChooseColorDialog(String title, Color color) {
		// Shows the standard Swing dialog to choose a color.
		return JColorChooser.showDialog(this, title, color);
	}


	// Private inner class for the ActionListener implementation.
	private class ListenerImpl implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == chooseFirstColorMenuItem) {
				chooseFirstColor();
			} else if (source == chooseSecondColorMenuItem) {
				chooseSecondColor();
			} else if (source == horizontalOrientationMenuItem) {
				setHorizontalOrientation();
			} else if (source == verticalOrientationMenuItem) {
				setVerticalOrientation();
			}
		}
	}
}