package com.sinius15.pibot.pi;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class PiServer implements Runnable, GyroEvent{

	PiServer thiss = this;
	
	int pin1 = 0, pin2 = 7, pin3 = 2, pin4 = 3;
	
	HashMap<String, CommandAction> commands = new HashMap<>();
	
	Alg algoritmeHandler = new Alg();
	ServerSocket serverSock;
	ArrayList<Client> clients = new ArrayList<>();
	
	Thread clientReceiver;
	
	boolean running = true;
	
	int leftWheelSpeed, rightWheelSpeed, batteryPower;
	float roll, pich, yaw;
	
	public float nullPoint = 0;
	
	public PiServer(){
		System.out.println("Starting program");
		
		initGPIO();
		initCommands();

		System.out.println("Starting server");
		try {
			serverSock = new ServerSocket(4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Starting server done");
		
		System.out.println("Starting clientaccepter");
		clientReceiver = new Thread(this);
		clientReceiver.start();
		System.out.println("Starting clientaccepter done");
		
		System.out.println("Starting gyro receiver");
		new GyroReceiver(this);
		System.out.println("Starting gyro receiver done");
		
		System.out.println("Starting vertical keeper...");
		new Thread(verticalKeeper).start();
		System.out.println("Starting vertical keeper done");
	}
	
	public void initGPIO(){
		System.out.println("Initializing gpio pins");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(pin1, 0, 100);
		SoftPwm.softPwmCreate(pin2, 0, 100);
		SoftPwm.softPwmCreate(pin3, 0, 100);
		SoftPwm.softPwmCreate(pin4, 0, 100);
		System.out.println("Initializing gpio pins done");
	}
	
	public void initCommands(){
		System.out.println("Initializing commands");
		commands.put("setNullPoint", new CommandAction() { @Override public void onCommand(String cmd) {
			nullPoint = pich;
			System.out.println("new nullpoint is " + nullPoint);
		}});
		commands.put("shutdown", new CommandAction() { @Override public void onCommand(String cmd) {
			System.out.println("shutting down PiBot...");
			try {
				serverSock.close();
				for(Client c : clients)
					c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			running = false;
		}});
		System.out.println("Initializing commands done");
	}
	
	Runnable verticalKeeper = new Runnable() {
		
		@Override
		public void run() {
			while(running){
				algoritmeHandler.doAlgoritme(thiss);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	@Override
	public void run() {
		while(running){
			try {
				System.out.println("accepting clients...");
				clients.add(new Client(this, serverSock.accept()));
				System.out.println("client added to the client list!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new PiServer();
	}

	public void onCommand(String line) {
		System.out.println("received command: " + line);
		for(String key : commands.keySet()){
			if(line.startsWith(key)){
				commands.get(key).onCommand(line);
			}
		}
	}
	
	/**
	 * speed 0 is off, 100 is full speed and -100 is backwards full speed.
	 */
	public void setLeftWheelSpeed(int speed) {
		speed = speed > 100 ? 100 : speed;
		speed = speed < -100 ? -100 : speed;
		leftWheelSpeed = speed;
		if(speed > 0 ){ //forward
			SoftPwm.softPwmWrite(pin1, 0);
			SoftPwm.softPwmWrite(pin2, speed);
		}else if(speed < 0){ //backwards
			SoftPwm.softPwmWrite(pin1, Math.abs(speed));
			SoftPwm.softPwmWrite(pin2, 0);
		}else{
			SoftPwm.softPwmWrite(pin1, 0);
			SoftPwm.softPwmWrite(pin2, 0);
		}
	}

	/**
	 * speed 0 is off, 100 is full speed and -100 is backwards full speed.
	 */
	public void setRightWheelSpeed(int speed) {
		speed = speed > 100 ? 100 : speed;
		speed = speed < -100 ? -100 : speed;
		rightWheelSpeed = speed;
		if(speed > 0 ){ //forward
			SoftPwm.softPwmWrite(pin3, 0);
			SoftPwm.softPwmWrite(pin4, speed);
		}else if(speed < 0){ //backwards
			SoftPwm.softPwmWrite(pin3, Math.abs(speed));
			SoftPwm.softPwmWrite(pin4, 0);
		}else{
			SoftPwm.softPwmWrite(pin3, 0);
			SoftPwm.softPwmWrite(pin4, 0);
		}

	}
	
	@Override
	public void onRollChange(float newRoll) {
		this.roll = newRoll;
	}

	@Override
	public void onPichChange(float newPich) {
		this.pich = newPich;
	}

	@Override
	public void onYawChange(float newYaw) {
		this.yaw = newYaw;
	}

}
