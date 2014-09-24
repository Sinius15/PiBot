package com.sinius15.pibot.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyLED", PinState.HIGH);


		Thread.sleep(5000);
		pin.low();

		Thread.sleep(5000);

		pin.toggle();


		Thread.sleep(5000);

		pin.toggle();


		Thread.sleep(5000);

		pin.pulse(1000, true); 
		
		gpio.shutdown();
	}

}
