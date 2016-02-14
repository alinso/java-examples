/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

/*
 * KeyActionExecutor is an interface that represents the strategy to execute
 * key actions. Implementations of this interface should use key actions in a
 * concrete, real, way (e.g. on the java.awt.Robot class).
 */
public interface KeyActionExecutor {
	void executeKeyActions(KeyAction[] keyActions) throws Exception;
}