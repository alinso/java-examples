/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class LabelHtmlDemoFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public LabelHtmlDemoFrame() {
		super("Label Html Demo - Java Example by andbin");

		String homeImageUrl = getResourceUrlForHtml("icons/home.png");
		String worldImageUrl = getResourceUrlForHtml("icons/world.png");

		String html = "<html>"
		            + "<head>"
		            + "<style>"
		            + "body { margin: 10pt; font-weight: normal; font-size: 14pt; }"
		            + "p { margin-bottom: 6pt; }"
		            + "</style>"
		            + "</head>"
		            + "<body>"
		            + "<h1 style='margin-top:0'>Label Html Demo</h1>"
		            + "<p>This is a single <code>javax.swing.JLabel</code> with HTML markup.</p>"
		            + "<p>It contains <font color='red'>colored</font>, "
		            + "<b>bold</b>, <i>italic</i> text and also images in a "
		            + "<code>&lt;table&gt;</code>.</p>"
		            + "<table border='1' style='margin:15pt;'>"
		            + "<tr><td><img src='" + homeImageUrl + "'></td><td>home icon</td></tr>"
		            + "<tr><td><img src='" + worldImageUrl + "'></td><td>world icon</td></tr>"
		            + "</table>"
		            + "<p>The label also has a blue line border applied.</p>"
		            + "</body>"
		            + "</html>";

		JLabel label = new JLabel(html);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());

		// A single component added in GridBagLayout without a GridBagConstraints
		// is sufficient to center the component into the content pane.
		contentPane.add(label);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 450);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private String getResourceUrlForHtml(String resourceName) {
		URL url = getClass().getResource(resourceName);

		if (url == null) {
			return null;
		}

		String urlString = url.toString().replaceAll("'", "%27").replaceAll("\"", "%22");
		return urlString;
	}
}