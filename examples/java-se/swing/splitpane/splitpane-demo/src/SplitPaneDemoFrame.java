/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

public class SplitPaneDemoFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private EllipsePaintPanel ellipsePaintPanel1;
	private EllipsePaintPanel ellipsePaintPanel2;
	private EllipsePaintPanel ellipsePaintPanel3;
	private JSplitPane splitPaneV;
	private JSplitPane splitPaneH;

	public SplitPaneDemoFrame() {
		super("Split Pane Demo - Java Example by andbin");

		ellipsePaintPanel1 = new EllipsePaintPanel();
		ellipsePaintPanel1.setBackground(Color.WHITE);
		ellipsePaintPanel1.setForeground(Color.RED);
		ellipsePaintPanel1.setPreferredSize(new Dimension(180, 100));
		ellipsePaintPanel1.setMinimumSize(new Dimension(40, 40));

		ellipsePaintPanel2 = new EllipsePaintPanel();
		ellipsePaintPanel2.setBackground(Color.WHITE);
		ellipsePaintPanel2.setForeground(Color.GREEN);
		ellipsePaintPanel2.setPreferredSize(new Dimension(180, 200));
		ellipsePaintPanel2.setMinimumSize(new Dimension(80, 80));

		ellipsePaintPanel3 = new EllipsePaintPanel();
		ellipsePaintPanel3.setBackground(Color.WHITE);
		ellipsePaintPanel3.setForeground(Color.BLUE);
		ellipsePaintPanel3.setPreferredSize(new Dimension(200, 200));
		ellipsePaintPanel3.setMinimumSize(new Dimension(120, 120));

		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ellipsePaintPanel1, ellipsePaintPanel2);
		splitPaneV.setResizeWeight(0.5);
		splitPaneV.setContinuousLayout(true);

		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneV, ellipsePaintPanel3);
		splitPaneH.setResizeWeight(0.5);
		splitPaneH.setContinuousLayout(true);

		getContentPane().add(splitPaneH);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}