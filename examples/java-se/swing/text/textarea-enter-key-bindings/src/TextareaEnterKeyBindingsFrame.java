/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;

public class TextareaEnterKeyBindingsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel infoLabel;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JPanel mainPanel;

	public TextareaEnterKeyBindingsFrame() {
		super("Textarea Enter Key Bindings - Java Example by andbin");

		infoLabel = new JLabel("<html>This is a JTextArea with the following special key bindings:<br>"
				+ "1) <font color='blue'>ENTER</font> : custom action to show a message dialog.<br>"
				+ "2) <font color='blue'>CTRL + ENTER</font> : standard action to insert a newline.");

		textArea = new JTextArea(6, 40);

		// Gets the Keymap from the JTextArea.
		Keymap keymap = textArea.getKeymap();

		// 1) Sets a new MyEnterAction bound to the VK_ENTER key code
		// with no modifiers (0). In this way, ENTER executes a custom action
		// instead of inserting a newline!
		keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				new MyEnterAction());  // custom action

		// 2) Sets a new InsertBreakAction bound to the VK_ENTER key code
		// with CTRL modifier. In this way, CTRL+ENTER inserts a newline!
		keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK),
				new DefaultEditorKit.InsertBreakAction());  // standard Swing action

		// Layouts the components.
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		mainPanel = new JPanel(new BorderLayout(0, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.add(infoLabel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		getContentPane().add(mainPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}


	private class MyEnterAction extends TextAction {
		private static final long serialVersionUID = 1L;

		public MyEnterAction() {
			super("my-enter-action");   // Name of this custom action.
		}

		public void actionPerformed(ActionEvent e) {
			JTextArea textArea = (JTextArea) getTextComponent(e);
			// Do what you want here ...

			JOptionPane.showMessageDialog(null, "ENTER pressed!");
		}
	}
}