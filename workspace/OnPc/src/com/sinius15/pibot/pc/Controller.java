package com.sinius15.pibot.pc;

import java.io.IOException;
import java.util.Arrays;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerListener;

import com.sinius15.graph.Graph;
import com.sinius15.pibot.pc.event.PhoneEvent;
import com.sinius15.pibot.pc.event.PiDataEvent;

public class Controller implements PhoneEvent, PiDataEvent, XboxControllerListener{

	PhoneDataConnection gyroReceiver;
	PiBotDataConnection piReceiver;
	ControlFrame frame;
	
	Setting roll = new Setting("Roll", "loading...");
	Setting pich = new Setting("Pich", "loading...");
	Setting yaw = new Setting("Yaw", "loading...");
	
	Setting batteryPower = new Setting("Battery Power", "loading...");
	Setting wheelSpeedLeft = new Setting("Wheel Speed Left", "loading...");
	Setting wheelSpeedRight = new Setting("Wheel Speed Right", "loading...");
	
	Setting phoneRotX = new Setting("Phone X rotation", "loading...");
	Setting phoneRotY = new Setting("Phone Y rotation", "loading...");
	Setting phoneRotZ = new Setting("Phone Z rotation", "loading...");
	
	Graph wheelSpeed, piRotation, phoneRotationGraph;
	
	public Controller(){


		
		phoneRotationGraph = new Graph(295, 241, 3, 200, 100, 100);
		
		frame = new ControlFrame(new Graph[]{wheelSpeed, piRotation, phoneRotationGraph, null},roll, pich, yaw, batteryPower, wheelSpeedLeft, 
				wheelSpeedRight, phoneRotX, phoneRotY, phoneRotZ);
		frame.setVisible(true);
		
		XboxController controller = new XboxController();
		controller.addXboxControllerListener(this);
		
		new Thread(phoneConnector, "phone connection therad").start();
		new Thread(piConnector, "pi connection therad").start();
	}
	
	Runnable piConnector = new Runnable() {
		@Override
		public void run() {
			connetToPi();
		}
	};
	
	Runnable phoneConnector = new Runnable() {
		@Override
		public void run() {
			connectToPhone();
		}
	};
	
	
	public void connetToPi(){
		try {
			System.out.println("connecting to PiBot...");
			piReceiver = new PiBotDataConnection();
			piReceiver.setEvent(this);
			System.out.println("connection to PiBot establisched!");
		} catch (IOException e) {
			System.out.println("connection to PiBot failed!");
			batteryPower.setData("Could not connect.");
			wheelSpeedLeft.setData("Could not connect.");
			wheelSpeedRight.setData("Could not connect.");
			roll.setData("Could not connect.");
			pich.setData("Could not connect.");
			yaw.setData("Could not connect.");
		}
	}
	
	public void connectToPhone(){
		try {
			System.out.println("connectiong to phone for gyro meter...");
			gyroReceiver = new PhoneDataConnection();
			gyroReceiver.setGyroEvent(this);
			System.out.println("connection to phone for gyro meter established!");
		} catch (IOException e) {
			System.out.println("connection to phone failed!");
			phoneRotX.setData("Could not connect.");
			phoneRotY.setData("Could not connect.");
			phoneRotZ.setData("Could not connect.");
		}
	}
	
	public static void main(String[] args) {
	
		
		
		//new Controller();
	}

	@Override
	public void onGyroValueChange() {
		phoneRotX.setData(""+gyroReceiver.getRotationX());
		phoneRotY.setData(""+gyroReceiver.getRotationY());
		phoneRotZ.setData(""+gyroReceiver.getRotationZ());
		phoneRotationGraph.lines[0].setData(addOnEnd(phoneRotationGraph.lines[0].data, (int)(gyroReceiver.getRotationX()*100 + 100)));
		phoneRotationGraph.lines[1].setData(addOnEnd(phoneRotationGraph.lines[1].data, (int)(gyroReceiver.getRotationY()*100 + 100)));
		phoneRotationGraph.lines[2].setData(addOnEnd(phoneRotationGraph.lines[2].data, (int)(gyroReceiver.getRotationZ()*100 + 100)));
		phoneRotationGraph.repaint();
	}

	@Override
	public void onGyroDisconnect() {
		phoneRotX.setData("Not connected.");
		phoneRotY.setData("Not connected.");
		phoneRotZ.setData("Not connected.");
	}

	@Override
	public void onPiDisconnect() {
		batteryPower.setData("Not connected.");
		wheelSpeedLeft.setData("Not connected.");
		wheelSpeedRight.setData("Not connected.");
		roll.setData("Not connected.");
		pich.setData("Not connected.");
		yaw.setData("Not connected.");
	}

	@Override
	public void onLeftWheelSpeedChange(int newSpeed) {
		wheelSpeedLeft.setData(""+newSpeed);
	}

	@Override
	public void onRightWheelSpeedChange(int newSpeed) {
		wheelSpeedRight.setData(""+newSpeed);
	}

	@Override
	public void onBatteryPowerChange(int newPower) {
		batteryPower.setData(""+newPower);
	}
	
	public static int[] addOnEnd(int[] oldArray, int toAdd){
		int[] newArr = Arrays.copyOfRange(oldArray, 1, oldArray.length+1);
		newArr[oldArray.length-1] = toAdd;
		return newArr;
	}

	@Override
	public void buttonA(boolean pressed) {
		piReceiver.setLeftWheelState( pressed ? WheelState.FORWARD : WheelState.OFF);
	}

	@Override
	public void buttonB(boolean pressed) {
		piReceiver.setLeftWheelState(pressed ? WheelState.BACKWARD : WheelState.OFF);
	}

	@Override
	public void buttonX(boolean pressed) {
		piReceiver.setRightWheelState(pressed ? WheelState.BACKWARD : WheelState.OFF);
	}

	@Override
	public void buttonY(boolean pressed) {
		piReceiver.setRightWheelState(pressed ? WheelState.FORWARD : WheelState.OFF);
	}

	@Override
	public void back(boolean pressed) {
		piReceiver.sendMessage("setNullPoint");
	}

	@Override
	public void start(boolean pressed) {
		if(pressed)
			piReceiver.disconnect();
	}

	@Override
	public void leftShoulder(boolean pressed) {
		
	}

	@Override
	public void rightShoulder(boolean pressed) {
		
	}

	@Override
	public void leftThumb(boolean pressed) {
		
	}

	@Override
	public void rightThumb(boolean pressed) {
		
	}

	@Override
	public void dpad(int direction, boolean pressed) {
		
	}

	@Override
	public void leftTrigger(double value) {
		
	}

	@Override
	public void rightTrigger(double value) {
		
	}

	@Override
	public void leftThumbMagnitude(double magnitude) {
		
	}

	@Override
	public void leftThumbDirection(double direction) {
		
	}

	@Override
	public void rightThumbMagnitude(double magnitude) {
		
	}

	@Override
	public void rightThumbDirection(double direction) {
		
	}

	@Override
	public void isConnected(boolean connected) {
		
	}

	@Override
	public void onRollChange(float newRoll) {
		roll.setData(""+newRoll);
	}

	@Override
	public void onPichChange(float newPich) {
		pich.setData(""+newPich);
		
	}

	@Override
	public void onYawChange(float newYaw) {
		yaw.setData(""+newYaw);
	}
}
