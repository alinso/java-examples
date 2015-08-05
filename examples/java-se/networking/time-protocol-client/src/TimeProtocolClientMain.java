/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeProtocolClientMain {
	// NIST Internet Time Server
	// Please, read carefully: http://tf.nist.gov/tf-cgi/servers.cgi
	private static final String NIST_TIME_SERVER_HOST = "time.nist.gov";

	public static void main(String[] args) {
		String host;
		int port = TcpTimeProtocolClient.TIME_PROTOCOL_PORT;

		try {
			if (args.length == 1) {
				host = args[0];
			} else if (args.length == 2) {
				host = args[0];
				port = Integer.parseInt(args[1]);
			} else {
				host = NIST_TIME_SERVER_HOST;
			}

			showTime(host, port);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private static void showTime(String host, int port) throws IOException {
		System.out.println("Getting time from: " + host + " / port " + port);

		TcpTimeProtocolClient tpc = new TcpTimeProtocolClient(host, port);

		Date networkTime = tpc.readTime();
		Date machineTime = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z", Locale.ENGLISH);

		System.out.println("Network time: " + sdf.format(networkTime));
		System.out.println("Machine time: " + sdf.format(machineTime));
	}
}