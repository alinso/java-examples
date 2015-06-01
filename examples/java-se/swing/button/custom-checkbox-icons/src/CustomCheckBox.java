/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox {
	private static final long serialVersionUID = 1L;

	public CustomCheckBox(String text) {
		super(text);

		// Loads image icons for all the checkbox states.
		ImageIcon normalUnselectedIcon = loadIcon("checkbox-normal-unselected.png");
		ImageIcon normalSelectedIcon = loadIcon("checkbox-normal-selected.png");
		ImageIcon rolloverUnselectedIcon = loadIcon("checkbox-rollover-unselected.png");
		ImageIcon rolloverSelectedIcon = loadIcon("checkbox-rollover-selected.png");
		ImageIcon disabledUnselectedIcon = loadIcon("checkbox-disabled-unselected.png");
		ImageIcon disabledSelectedIcon = loadIcon("checkbox-disabled-selected.png");
		ImageIcon pressedIcon = loadIcon("checkbox-pressed.png");

		// Sets normal (unselected/selected) icons.
		setIcon(normalUnselectedIcon);
		setSelectedIcon(normalSelectedIcon);

		// Sets rollover (unselected/selected) icons.
		setRolloverEnabled(true);
		setRolloverIcon(rolloverUnselectedIcon);
		setRolloverSelectedIcon(rolloverSelectedIcon);

		// Sets disabled (unselected/selected) icons.
		setDisabledIcon(disabledUnselectedIcon);
		setDisabledSelectedIcon(disabledSelectedIcon);

		// Sets pressed icon.
		setPressedIcon(pressedIcon);
	}

	private ImageIcon loadIcon(String name) {
		// Finds the image icon using the "resource" concept.
		URL imageUrl = getClass().getResource("icons/" + name);
		return new ImageIcon(imageUrl);
	}
}