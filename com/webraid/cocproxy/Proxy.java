package com.webraid.cocproxy;

import java.net.ServerSocket;
import java.net.Socket;

import com.webraid.cocproxy.network.Client;
import com.webraid.cocproxy.network.Server;
import com.webraid.cocproxy.utils.Log;

public class Proxy {

	private static ServerSocket server;
	private static int port = 9339;
	private static String originalHost = "game.clashofclans.com";
	private static boolean consoleDebug = false;

	public static void start() {
		Log.init();
		Log.logInfo("* Proxy started");
		new Thread(new Runnable() {

			@Override
			public void run() {
				Proxy.startThreadServer();
			}

		}).start();
	}

	public static void startThreadServer() {
		try {
			server = new ServerSocket(port);
			Socket clientSocket = server.accept();
			new Thread(new Server(originalHost)).start();
			new Thread(new Client(clientSocket)).start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static boolean canDebug(){
		return consoleDebug;
	}

}
