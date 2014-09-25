package com.sinius15.pibot.pc;

import java.io.IOException;

import com.sinius15.pibot.pc.event.GyroEvent;
import com.sinius15.pibot.pc.event.PiDataEvent;

public class Controller implements GyroEvent, PiDataEvent{

	GyroDataReceiver gyroReceiver;
	PiBotDataReceiver piReceiver;
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
		try {
			piReceiver = new PiBotDataReceiver();
			piReceiver.setEvent(this);
		} catch (IOException e) {
			frame.batteryPower.setText("Could not connect.");
			frame.wheelSpeedLeft.setText("Could not connect.");
			frame.wheelSpeedRight.setText("Could not connect.");
			frame.piBotTimer.setText("Could not connect.");
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

	@Override
	public void onPiDisconnect() {
		frame.batteryPower.setText("Not connected.");
		frame.wheelSpeedLeft.setText("Not connected.");
		frame.wheelSpeedRight.setText("Not connected.");
		frame.piBotTimer.setText("Not connected.");
	}

	@Override
	public void onLeftWheelSpeedChange(int newSpeed) {
		frame.wheelSpeedLeft.setText(""+newSpeed);
	}

	@Override
	public void onRightWheelSpeedChange(int newSpeed) {
		frame.wheelSpeedRight.setText(""+newSpeed);
	}

	@Override
	public void onBatteryPowerChange(int newPower) {
		frame.batteryPower.setText(""+newPower);
	}

	@Override
	public void onPiTimeChange(int newTime) {
		frame.piBotTimer.setText("" + newTime);
	}
	
}
