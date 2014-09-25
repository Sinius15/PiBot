package com.sinius15.pibot.pc.event;

public interface PiDataEvent {

	public void onPiDisconnect();
	public void onLeftWheelSpeedChange(int newSpeed);
	public void onRightWheelSpeedChange(int newSpeed);
	public void onBatteryPowerChange(int newPower);
	public void onPiTimeChange(int newTime);
	
}
