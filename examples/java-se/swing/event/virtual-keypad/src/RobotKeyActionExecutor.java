/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.AWTException;
import java.awt.Robot;

/*
 * RobotKeyActionExecutor is an implementation of KeyActionExecutor that executes
 * key actions using the well known java.awt.Robot class.
 */
public class RobotKeyActionExecutor implements KeyActionExecutor {
	private Robot robot;

	public RobotKeyActionExecutor() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// RuntimeException is just for example. A more real application
			// could throw something like a KeyActionExecutorException.
			throw new RuntimeException("Unable to create Robot for RobotKeyActionExecutor");
		}
	}

	public void executeKeyActions(KeyAction[] keyActions) throws Exception {
		for (KeyAction keyAction : keyActions) {
			switch (keyAction.getMode()) {
			case PRESS:
				robot.keyPress(keyAction.getCode());
				robot.delay(20);
				break;
			case RELEASE:
				robot.keyRelease(keyAction.getCode());
				robot.delay(20);
				break;
			case TYPE:
				robot.keyPress(keyAction.getCode());
				robot.delay(20);
				robot.keyRelease(keyAction.getCode());
				robot.delay(20);
				break;
			}
		}
	}
}