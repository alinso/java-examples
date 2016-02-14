/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

/*
 * KeyAction defines immutable objects representing an "action" of a keyboard key.
 */
public class KeyAction {
	private final int code;
	private final Mode mode;

	public KeyAction(int code, Mode mode) {
		if (mode == null) {
			throw new IllegalArgumentException("mode cannot be null");
		}

		this.code = code;
		this.mode = mode;
	}

	public int getCode() {
		return code;
	}

	public Mode getMode() {
		return mode;
	}

	// Factory method for a "press" action.
	public static KeyAction forKeyPress(int code) {
		return new KeyAction(code, Mode.PRESS);
	}

	// Factory method for a "release" action.
	public static KeyAction forKeyRelease(int code) {
		return new KeyAction(code, Mode.RELEASE);
	}

	// Factory method for a "type" action.
	public static KeyAction forKeyType(int code) {
		return new KeyAction(code, Mode.TYPE);
	}


	public enum Mode {
		PRESS,     // press of a key
		RELEASE,   // release of a key
		TYPE       // type (press & release) of a key
	}
}