package com.sinius15.pibot.pc;

import java.io.IOException;

import com.sinius15.pibot.pc.event.PiDataEvent;

public class PiBotDataConnection extends DataConnection {

	private int leftWheelSpeed, rightWheelSpeed, batteryPower;
	private float roll, pich, yaw;

	public static final String IP = "10.233.0.132";
	public static final int PORT = 4444;

	private PiDataEvent event;

	public PiBotDataConnection() throws IOException {
		connect(IP, PORT);
	}

	@Override
	protected void messageReceived(String msg) {
		String[] split = msg.split(";");
		for (String s : split) {
			String[] t = s.split("=");
			String varName = t[0];
			float value = Float.parseFloat(t[1]);
			switch (varName) {
			case "leftWheelSpeed":
				leftWheelSpeed = (int) value;
				event.onLeftWheelSpeedChange((int) value);
				break;
			case "rightWheelSpeed":
				rightWheelSpeed = (int) value;
				event.onRightWheelSpeedChange((int) value);
				break;
			case "batteryPower":
				batteryPower = (int) value;
				event.onBatteryPowerChange((int) value);
				break;
			case "roll":
				roll = value;
				event.onRollChange(value);
				break;
			case "pich":
				pich = value;
				event.onPichChange(value);
				break;
			case "yaw":
				yaw = value;
				event.onYawChange(value);
				break;
			default:
				break;
			}
		}
	}

	public void setLeftWheelState(WheelState state) {
		switch (state) {
		case BACKWARD:
			sendMessage("1On");
			sendMessage("2Off");
			break;
		case FORWARD:
			sendMessage("1Off");
			sendMessage("2On");
			break;
		case OFF:
			sendMessage("1Off");
			sendMessage("2Off");
			break;
		}
	}

	public void setRightWheelState(WheelState state) {
		switch (state) {
		case BACKWARD:
			sendMessage("3On");
			sendMessage("4Off");
			break;
		case FORWARD:
			sendMessage("3Off");
			sendMessage("4On");
			break;
		case OFF:
			sendMessage("3Off");
			sendMessage("4Off");
			break;
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

	public PiDataEvent getEvent() {
		return event;
	}

	public void setEvent(PiDataEvent event) {
		this.event = event;
	}

	public void sendDisconnectMessage() {
		sendMessage("shutdown");
		disconnect();
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public float getPich() {
		return pich;
	}

	public void setPich(float pich) {
		this.pich = pich;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
}
