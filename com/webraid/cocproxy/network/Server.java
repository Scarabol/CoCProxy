package com.webraid.cocproxy.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.webraid.cocproxy.utils.Log;
import com.webraid.cocproxy.utils.Packet;
import com.webraid.cocproxy.utils.Packet.PacketSide;

public class Server implements Runnable {

	private Socket socket;
	private InputStream is;
	private static OutputStream os;
	
	private byte[] reply = new byte[4096];

	public Server(String originalHost) throws Exception {
		socket = new Socket(originalHost, 9339);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		Log.logInfo("* Connected to original server");
	}

	@Override
	public void run() {
		int bytes_read;
		try {
			while ((bytes_read = is.read(reply)) != -1) {
				Client.getOutputStream().write(reply, 0, bytes_read);
				Client.getOutputStream().flush();
				Packet currentPacket = new Packet(reply, PacketSide.FROM_SERVER);
				Log.logPacket(currentPacket);
			}
		} catch (IOException e) {
		}
	}

	public static OutputStream getOuputStream() {
		return os;
	}

}
