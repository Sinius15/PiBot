package com.sinius15.pibot.pi;

public interface GyroEvent {

	public void onRollChange(float newRoll);
	public void onPichChange(float newPich);
	public void onYawChange(float newYaw);
	
}
