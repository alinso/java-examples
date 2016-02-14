/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VirtualKeypadPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private KeyActionExecutor keyActionExecutor;
	private ActionListenerImpl actionListener;
	private GridBagLayout gridBagLayout;
	private Font buttonFont;

	public VirtualKeypadPanel(KeyActionExecutor keyActionExecutor) {
		this.keyActionExecutor = keyActionExecutor;

		actionListener = new ActionListenerImpl();
		gridBagLayout = new GridBagLayout();
		buttonFont = new JButton().getFont().deriveFont(20.0f);

		setLayout(gridBagLayout);
		setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		addKeyButton(0, 0, 1, "7", KeyAction.forKeyType(KeyEvent.VK_7));
		addKeyButton(1, 0, 1, "8", KeyAction.forKeyType(KeyEvent.VK_8));
		addKeyButton(2, 0, 1, "9", KeyAction.forKeyType(KeyEvent.VK_9));

		addKeyButton(0, 1, 1, "4", KeyAction.forKeyType(KeyEvent.VK_4));
		addKeyButton(1, 1, 1, "5", KeyAction.forKeyType(KeyEvent.VK_5));
		addKeyButton(2, 1, 1, "6", KeyAction.forKeyType(KeyEvent.VK_6));

		addKeyButton(0, 2, 1, "1", KeyAction.forKeyType(KeyEvent.VK_1));
		addKeyButton(1, 2, 1, "2", KeyAction.forKeyType(KeyEvent.VK_2));
		addKeyButton(2, 2, 1, "3", KeyAction.forKeyType(KeyEvent.VK_3));

		addKeyButton(0, 3, 2, "0", KeyAction.forKeyType(KeyEvent.VK_0));
		addKeyButton(2, 3, 1, ".", KeyAction.forKeyType(KeyEvent.VK_PERIOD));
	}

	private void addKeyButton(int gridx, int gridy, int columns,
			String buttonText, KeyAction... keyActions) {
		KeyButton keyButton = new KeyButton(buttonText, keyActions);
		keyButton.setFont(buttonFont);   // Same Font for all buttons.

		addButton(gridx, gridy, columns, keyButton);
	}

	private void addButton(int gridx, int gridy, int columns, KeyButton keyButton) {
		keyButton.addActionListener(actionListener);   // Same ActionListener for all buttons.

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = columns;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(4, 4, 4, 4);

		add(keyButton, gbc);
	}


	private class ActionListenerImpl implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Expects that the "source" of the event is only a KeyButton.
			KeyButton keyButton = (KeyButton) e.getSource();

			try {
				// Executes the key actions using the configured executor.
				keyActionExecutor.executeKeyActions(keyButton.getKeyActions());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}