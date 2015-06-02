/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class TextfieldScrollbarFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JScrollBar scrollBar;

	public TextfieldScrollbarFrame() {
		super("Textfield Scrollbar - Java Example by andbin");

		String text = "This is a quite long line to show how to use a single "
				+ "JScrollBar to handle the horizontal scrolling of the text in "
				+ "a JTextField component.";

		textField = new JTextField(text);
		textField.setCaretPosition(0);
		Font textFont = textField.getFont();
		textField.setFont(textFont.deriveFont(textFont.getSize2D() * 1.5F));
		scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);

		// JTextField has a BoundedRangeModel to manage the visibility/scrolling
		// of the text. JScrollBar can directly use this model.
		BoundedRangeModel model = textField.getHorizontalVisibility();
		scrollBar.setModel(model);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 4));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(textField, BorderLayout.CENTER);
		panel.add(scrollBar, BorderLayout.SOUTH);

		getContentPane().add(panel, BorderLayout.NORTH);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}