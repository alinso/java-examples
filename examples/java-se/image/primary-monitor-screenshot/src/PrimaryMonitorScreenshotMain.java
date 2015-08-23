/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PrimaryMonitorScreenshotMain {
	public static void main(String[] args) {
		try {
			BufferedImage screenshotImage = createPrimaryMonitorScreenshot();

			// Writes the screenshot image to a file in PNG format.
			ImageIO.write(screenshotImage, "png", new File("screenshot.png"));
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private static BufferedImage createPrimaryMonitorScreenshot() throws AWTException, IOException {
		// Gets the size of the primary screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Creates the capture rectangle for the primary screen.
		// Note that the top-left corner is implicitly (0, 0).
		Rectangle captureRect = new Rectangle(screenSize);

		// Creates the Robot for the primary screen.
		Robot robot = new Robot();

		System.out.print("Capturing screenshot for primary screen ("
				+ screenSize.width + " x " + screenSize.height + ") ... ");

		long t1 = System.currentTimeMillis();

		// Captures the content of the primary screen.
		// NOTE: the mouse cursor is *not* included in image.
		BufferedImage screenshotImage = robot.createScreenCapture(captureRect);

		long t2 = System.currentTimeMillis();
		System.out.println("in " + (t2-t1) + " milliseconds");

		return screenshotImage;
	}
}