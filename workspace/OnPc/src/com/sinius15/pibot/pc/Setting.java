package com.sinius15.pibot.pc;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Setting {

	public JLabel label = new JLabel();
	
	public JTextArea field = new JTextArea();
	
	public Setting(String title, String initialValue){
		label.setText(title);
		field.setText(initialValue);
		field.setEditable(false);
	}
	
	public void setData(String data){
		field.setText(data);
	}
}
