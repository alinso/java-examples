/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

// This class is a simple implementation of a "client" for the Time Protocol.
//
// Please, see: http://en.wikipedia.org/wiki/TIME_protocol
// for more informations on the Time Protocol (RFC-868).

public class TcpTimeProtocolClient {
	public static final int TIME_PROTOCOL_PORT = 37;

	// The number of seconds between January 1, 1900, 00:00:00 GMT
	// and January 1, 1970, 00:00:00 GMT
	public static final long EPOCH_OFFSET = 2208988800L;

	private InetAddress address;
	private int port;

	public TcpTimeProtocolClient(String host) throws UnknownHostException {
		this(host, TIME_PROTOCOL_PORT);
	}

	public TcpTimeProtocolClient(String host, int port) throws UnknownHostException {
		this(InetAddress.getByName(host), port);
	}

	public TcpTimeProtocolClient(InetAddress address) {
		this(address, TIME_PROTOCOL_PORT);
	}

	public TcpTimeProtocolClient(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	public Date readTime() throws IOException {
		Socket s = new Socket(address, port);

		try {
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);

			// Reads a 32 bit value (4 bytes) in Big-Endian order.
			// The unsigned value is the number of seconds elapsed since
			// January 1, 1900, 00:00:00 GMT
			int t = dis.readInt();

			// Converts to milliseconds from "the epoch" (January 1, 1970, 00:00:00 GMT)
			long millisFromEpoch = ((t & 0xFFFFFFFFL) - EPOCH_OFFSET) * 1000L;
			return new Date(millisFromEpoch);
		} finally {
			s.close();
		}
	}
}