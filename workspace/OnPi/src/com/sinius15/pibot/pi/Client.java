package com.sinius15.pibot.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private Socket sock;

	private PrintWriter out;
	private BufferedReader input;
	private Thread listener, sender;

	private PiServer parent;

	private boolean isOpen = true;

	public Client(PiServer parent, Socket sock) throws IOException {
		this.sock = sock;
		this.parent = parent;

		out = new PrintWriter(sock.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

		listener = new Thread(inputListener);
		listener.start();

		sender = new Thread(outputSender);
		sender.start();
	}

	Runnable inputListener = new Runnable() {
		@Override
		public void run() {
			try {
				String line;
				while ((line = input.readLine()) != null) {
					parent.onCommand(line);
				}
			} catch (IOException e) {

			} finally {
				try {
					input.close();
					sock.close();
					parent.clients.remove(this);
					isOpen = false;
					System.out.println("client disconnected.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	Runnable outputSender = new Runnable() {
		@Override
		public void run() {
			while (isOpen) {
				out.println("leftWheelSpeed=" + parent.leftWheelSpeed
						+ ";rightWheelSpeed=" + parent.rightWheelSpeed
						+ ";batteryPower=" + parent.batteryPower + ";roll="
						+ parent.roll + ";pich=" + parent.pich + ";yaw="
						+ parent.yaw);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public void sendMessage(String message) {
		out.println(message);
	}

	public void close() throws IOException {
		sock.close();
	}
}
