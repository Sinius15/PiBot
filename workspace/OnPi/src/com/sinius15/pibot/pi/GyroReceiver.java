package com.sinius15.pibot.pi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GyroReceiver implements Runnable {

	GyroEvent event;
	
	public GyroReceiver(GyroEvent event) {
		this.event = event;
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			Process p = Runtime.getRuntime ().exec ("sudo minimu9-ahrs --output euler -b /dev/i2c-1 ");
			
			InputStream stream = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader in = new BufferedReader(reader);
			
			String input;
			System.out.println("Now reading input from gyro meter...");
			while((input = in.readLine()) != null){
				float[] vals = toFloat(input.trim().split("\\s+"));
				event.onYawChange(vals[0]);
				event.onPichChange(vals[1]);
				event.onRollChange(vals[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	float[] toFloat(String[] stringArr){
		float[] out = new float[stringArr.length];
		for(int i = 0; i < stringArr.length; i++){
			out[i] = Float.parseFloat(stringArr[i]);
		}
		return out;
	}

}
