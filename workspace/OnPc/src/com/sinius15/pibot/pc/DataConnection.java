package com.sinius15.pibot.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class DataConnection implements Runnable{

	protected abstract void messageReceived(String msg);
	protected abstract void onDisconnect();
	protected abstract void onConnect();
	
	private Socket socket;
	private BufferedReader input;
	private Thread listener;
    private PrintWriter out;
	
	protected void connect(String ip, int port) throws UnknownHostException, IOException{
		socket = new Socket(ip, port);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		listener = new Thread(this);
		listener.start();
		onConnect();
	}
	
	protected void sendMessage(String message){
		out.println(message);
	}
	
	@Override
	public void run() {
		try {
			String line;
			while((line = input.readLine()) != null){
				messageReceived(line);
			}
		} catch (IOException e) {
			
		} finally {
			disconnect();
		}
	}
	
	public boolean isConnected(){
		return !socket.isClosed();
	}
	
	public void disconnect(){
		try {
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			onDisconnect();
		}
	}
	
}
