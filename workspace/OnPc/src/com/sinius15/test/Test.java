package com.sinius15.test;

import java.io.IOException;

import com.sinius15.pibot.pc.GyroDataReceiver;

public class Test {

	public static void main(String[] args) {
		try {
			GyroDataReceiver r = new GyroDataReceiver();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
