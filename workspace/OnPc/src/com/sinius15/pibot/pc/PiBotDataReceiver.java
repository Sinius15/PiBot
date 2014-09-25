package com.sinius15.pibot.pc;

import java.io.IOException;

import com.sinius15.pibot.pc.event.PiDataEvent;

public class PiBotDataReceiver extends DataReceiver{

	private int leftWheelSpeed, rightWheelSpeed, batteryPower, runningTime;
	
	public static final String IP = "10.233.0.130";
	public static final int PORT = 4444;
	
	private PiDataEvent event;
	
	public PiBotDataReceiver() throws IOException{
		connect(IP, PORT);
	}
	
	@Override
	protected void messageReceived(String msg) {
		String[] split = msg.split(";");
		for(String s : split){
			String[] t = s.split("=");
			String varName = t[0];
			int  value = Integer.parseInt(t[1]);
			switch (varName) {
			case "leftWheelSpeed":
				leftWheelSpeed = value;
				event.onLeftWheelSpeedChange(value);
				break;
			case "rightWheelSpeed":
				rightWheelSpeed = value;
				event.onRightWheelSpeedChange(value);
				break;
			case "batteryPower":
				batteryPower = value;
				event.onBatteryPowerChange(value);
				break;
			case "runningTime":
				runningTime = value;
				event.onPiTimeChange(value);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void onDisconnect() {
		event.onPiDisconnect();
	}

	@Override
	protected void onConnect() {
		
	}

	public int getLeftWheelSpeed() {
		return leftWheelSpeed;
	}
	public int getRightWheelSpeed() {
		return rightWheelSpeed;
	}
	public int getBatteryPower() {
		return batteryPower;
	}
	public int getRunningTime() {
		return runningTime;
	}

	public PiDataEvent getEvent() {
		return event;
	}

	public void setEvent(PiDataEvent event) {
		this.event = event;
	}
}
