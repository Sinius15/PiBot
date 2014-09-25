package com.sinius15.pibot.pc;

import java.io.IOException;

import com.sinius15.pibot.pc.event.GyroEvent;

public class Controller implements GyroEvent{

	GyroDataReceiver gyroReceiver;
	ControlFrame frame;
	
	public Controller(){
		frame = new ControlFrame();
		frame.setVisible(true);
		
		try {
			gyroReceiver = new GyroDataReceiver();
			gyroReceiver.setGyroEvent(this);
		} catch (IOException e) {
			frame.rotXField.setText("Could not connect.");
			frame.rotYField.setText("Could not connect.");
			frame.rotZField.setText("Could not connect.");
		}
	}
	
	public static void main(String[] args) {
		new Controller();
	}

	@Override
	public void onGyroValueChange() {
		frame.rotXField.setText(""+gyroReceiver.getRotationX());
		frame.rotYField.setText(""+gyroReceiver.getRotationY());
		frame.rotZField.setText(""+gyroReceiver.getRotationZ());
	}

	@Override
	public void onGyroDisconnect() {
		frame.rotXField.setText("Not connected.");
		frame.rotYField.setText("Not connected.");
		frame.rotZField.setText("Not connected.");
	}
	
}
