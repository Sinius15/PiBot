package com.sinius15.pibot.pc;

import java.io.IOException;

import com.sinius15.pibot.pc.event.GyroEvent;

/**
 * receives gyro data from the mobile phone on the pibot.
 * @author Sinius
 */
public class GyroDataReceiver extends DataReceiver{

	public static final String IP = "195.169.162.21";
	public static final int PORT = 5555;
	
	private GyroEvent event;
	
	private float rotationX, rotationY, rotationZ;
	
	public GyroDataReceiver() throws IOException{
		connect(IP, PORT);
	}

	@Override
	protected void messageReceived(String msg) {
		String[] value = msg.split(";");
		rotationX = Float.valueOf(value[0]).floatValue();
		rotationY = Float.valueOf(value[1]).floatValue();
		rotationZ = Float.valueOf(value[2]).floatValue();
		if(event != null){
			event.onGyroValueChange();
		}
	}

	@Override
	protected void onDisconnect() {
		event.onGyroDisconnect();
	}

	@Override
	protected void onConnect() {}
	
	public float getRotationX() {
		return rotationX;
	}
	public float getRotationY() {
		return rotationY;
	}
	public float getRotationZ() {
		return rotationZ;
	}
	public GyroEvent getGyroEvent() {
		return event;
	}
	public void setGyroEvent(GyroEvent event) {
		this.event = event;
	}

	
	
}
