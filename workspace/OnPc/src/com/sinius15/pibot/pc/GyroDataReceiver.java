package com.sinius15.pibot.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * receives gyro data from the mobile phone on the pibot.
 * @author Sinius
 */
public class GyroDataReceiver implements Runnable{

	public static final String IP = "10.233.0.117";
	public static final int PORT = 5555;
	
	private Socket socket;
	private BufferedReader input;
	private Thread listener;
	
	private float rotationX, rotationY, rotationZ;
	
	public GyroDataReceiver() throws IOException{
		socket = new Socket(IP, PORT);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		listener = new Thread(this);
		listener.start();
	}

	public void run() {
		try {
			String line;
			while((line = input.readLine()) != null){
				String[] value = line.split(";");
				rotationX = Float.valueOf(value[0]).floatValue();
				rotationY = Float.valueOf(value[1]).floatValue();
				rotationZ = Float.valueOf(value[2]).floatValue();
			}
		} catch (IOException e) {
			
		} finally {
			try {
				input.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public float getRotationX() {
		return rotationX;
	}

	public float getRotationY() {
		return rotationY;
	}
	public float getRotationZ() {
		return rotationZ;
	}
	public boolean isConnected(){
		return !socket.isClosed();
	}
	
}
