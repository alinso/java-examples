/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

public class DefaultElapsedTimeFormatter implements ElapsedTimeFormatter {
	public String format(long elapsedMilliseconds) {
		int tenthsOfSecond = (int) (elapsedMilliseconds % 1000 / 100);
		int seconds = (int) (elapsedMilliseconds / 1000 % 60);
		int minutes = (int) (elapsedMilliseconds / 60000 % 60);
		int hours = (int) (elapsedMilliseconds / 3600000);

		return String.format("%d:%02d:%02d.%d", hours, minutes, seconds, tenthsOfSecond);
	}
}