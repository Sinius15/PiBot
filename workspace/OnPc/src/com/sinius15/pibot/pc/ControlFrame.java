package com.sinius15.pibot.pc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ControlFrame extends JFrame {

	private static final long serialVersionUID = -8344848980034299589L;
	
	private JPanel contentPane;
	public JTextField rotXField, rotYField, rotZField, batteryPower, 
			ipPiBot, ipPhone, wheelSpeedLeft, wheelSpeedRight, ipController, piBotTimer;

	public ControlFrame() {
		setTitle("PiBot Controller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel rightPane = new JPanel();
		contentPane.add(rightPane);
		GridBagLayout gbl_rightPane = new GridBagLayout();
		gbl_rightPane.columnWidths = new int[] {87, 0, 0};
		gbl_rightPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 52, 0};
		gbl_rightPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_rightPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
				0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		rightPane.setLayout(gbl_rightPane);
		
		JLabel lblRotationX = new JLabel("Rotation X: ");
		GridBagConstraints gbc_lblRotationX = new GridBagConstraints();
		gbc_lblRotationX.anchor = GridBagConstraints.WEST;
		gbc_lblRotationX.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotationX.gridx = 0;
		gbc_lblRotationX.gridy = 0;
		rightPane.add(lblRotationX, gbc_lblRotationX);
		
		rotXField = new JTextField();
		rotXField.setEditable(false);
		rotXField.setText("loading...");
		GridBagConstraints gbc_rotXField = new GridBagConstraints();
		gbc_rotXField.insets = new Insets(0, 0, 5, 0);
		gbc_rotXField.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotXField.gridx = 1;
		gbc_rotXField.gridy = 0;
		rightPane.add(rotXField, gbc_rotXField);
		rotXField.setColumns(10);
		
		JLabel lblRotationY = new JLabel("Rotation Y: ");
		GridBagConstraints gbc_lblRotationY = new GridBagConstraints();
		gbc_lblRotationY.anchor = GridBagConstraints.WEST;
		gbc_lblRotationY.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotationY.gridx = 0;
		gbc_lblRotationY.gridy = 1;
		rightPane.add(lblRotationY, gbc_lblRotationY);
		
		rotYField = new JTextField();
		rotYField.setEditable(false);
		rotYField.setText("loading...");
		GridBagConstraints gbc_rotYField = new GridBagConstraints();
		gbc_rotYField.insets = new Insets(0, 0, 5, 0);
		gbc_rotYField.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotYField.gridx = 1;
		gbc_rotYField.gridy = 1;
		rightPane.add(rotYField, gbc_rotYField);
		rotYField.setColumns(10);
		
		JLabel lblRotationZ = new JLabel("Rotation Z: ");
		GridBagConstraints gbc_lblRotationZ = new GridBagConstraints();
		gbc_lblRotationZ.anchor = GridBagConstraints.WEST;
		gbc_lblRotationZ.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotationZ.gridx = 0;
		gbc_lblRotationZ.gridy = 2;
		rightPane.add(lblRotationZ, gbc_lblRotationZ);
		
		rotZField = new JTextField();
		rotZField.setEditable(false);
		rotZField.setText("loading...");
		GridBagConstraints gbc_rotZField = new GridBagConstraints();
		gbc_rotZField.insets = new Insets(0, 0, 5, 0);
		gbc_rotZField.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotZField.gridx = 1;
		gbc_rotZField.gridy = 2;
		rightPane.add(rotZField, gbc_rotZField);
		rotZField.setColumns(10);
		
		JLabel lblLeftWheelSpeed = new JLabel("Left Wheel Speed: ");
		GridBagConstraints gbc_lblLeftWheelSpeed = new GridBagConstraints();
		gbc_lblLeftWheelSpeed.anchor = GridBagConstraints.WEST;
		gbc_lblLeftWheelSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeftWheelSpeed.gridx = 0;
		gbc_lblLeftWheelSpeed.gridy = 3;
		rightPane.add(lblLeftWheelSpeed, gbc_lblLeftWheelSpeed);
		
		wheelSpeedLeft = new JTextField();
		wheelSpeedLeft.setText("loading...");
		wheelSpeedLeft.setEditable(false);
		GridBagConstraints gbc_wheelSpeedLeft = new GridBagConstraints();
		gbc_wheelSpeedLeft.insets = new Insets(0, 0, 5, 0);
		gbc_wheelSpeedLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_wheelSpeedLeft.gridx = 1;
		gbc_wheelSpeedLeft.gridy = 3;
		rightPane.add(wheelSpeedLeft, gbc_wheelSpeedLeft);
		wheelSpeedLeft.setColumns(10);
		
		JLabel lblRightWheelSpeed = new JLabel("Right Wheel Speed:");
		GridBagConstraints gbc_lblRightWheelSpeed = new GridBagConstraints();
		gbc_lblRightWheelSpeed.anchor = GridBagConstraints.WEST;
		gbc_lblRightWheelSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblRightWheelSpeed.gridx = 0;
		gbc_lblRightWheelSpeed.gridy = 4;
		rightPane.add(lblRightWheelSpeed, gbc_lblRightWheelSpeed);
		
		wheelSpeedRight = new JTextField();
		wheelSpeedRight.setText("loading...");
		wheelSpeedRight.setEditable(false);
		GridBagConstraints gbc_wheelSpeedRight = new GridBagConstraints();
		gbc_wheelSpeedRight.insets = new Insets(0, 0, 5, 0);
		gbc_wheelSpeedRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_wheelSpeedRight.gridx = 1;
		gbc_wheelSpeedRight.gridy = 4;
		rightPane.add(wheelSpeedRight, gbc_wheelSpeedRight);
		wheelSpeedRight.setColumns(10);
		
		JLabel lblBatteryPower = new JLabel("Battery Power:");
		GridBagConstraints gbc_lblBatteryPower = new GridBagConstraints();
		gbc_lblBatteryPower.anchor = GridBagConstraints.WEST;
		gbc_lblBatteryPower.insets = new Insets(0, 0, 5, 5);
		gbc_lblBatteryPower.gridx = 0;
		gbc_lblBatteryPower.gridy = 5;
		rightPane.add(lblBatteryPower, gbc_lblBatteryPower);
		
		batteryPower = new JTextField();
		batteryPower.setText("loading...");
		batteryPower.setEditable(false);
		GridBagConstraints gbc_batteryPower = new GridBagConstraints();
		gbc_batteryPower.insets = new Insets(0, 0, 5, 0);
		gbc_batteryPower.fill = GridBagConstraints.HORIZONTAL;
		gbc_batteryPower.gridx = 1;
		gbc_batteryPower.gridy = 5;
		rightPane.add(batteryPower, gbc_batteryPower);
		batteryPower.setColumns(10);
		
		JLabel lblPibotip = new JLabel("PiBot Ip:");
		GridBagConstraints gbc_lblPibotip = new GridBagConstraints();
		gbc_lblPibotip.anchor = GridBagConstraints.WEST;
		gbc_lblPibotip.insets = new Insets(0, 0, 5, 5);
		gbc_lblPibotip.gridx = 0;
		gbc_lblPibotip.gridy = 6;
		rightPane.add(lblPibotip, gbc_lblPibotip);
		
		ipPiBot = new JTextField();
		ipPiBot.setText("loading...");
		GridBagConstraints gbc_ipPiBot = new GridBagConstraints();
		gbc_ipPiBot.insets = new Insets(0, 0, 5, 0);
		gbc_ipPiBot.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipPiBot.gridx = 1;
		gbc_ipPiBot.gridy = 6;
		rightPane.add(ipPiBot, gbc_ipPiBot);
		ipPiBot.setColumns(10);
		
		JLabel lblPhoneIp = new JLabel("Phone Ip:");
		GridBagConstraints gbc_lblPhoneIp = new GridBagConstraints();
		gbc_lblPhoneIp.anchor = GridBagConstraints.WEST;
		gbc_lblPhoneIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneIp.gridx = 0;
		gbc_lblPhoneIp.gridy = 7;
		rightPane.add(lblPhoneIp, gbc_lblPhoneIp);
		
		ipPhone = new JTextField();
		ipPhone.setText("loading...");
		GridBagConstraints gbc_ipPhone = new GridBagConstraints();
		gbc_ipPhone.insets = new Insets(0, 0, 5, 0);
		gbc_ipPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipPhone.gridx = 1;
		gbc_ipPhone.gridy = 7;
		rightPane.add(ipPhone, gbc_ipPhone);
		ipPhone.setColumns(10);
		
		JLabel lblControllerIp = new JLabel("Controller Ip:");
		GridBagConstraints gbc_lblControllerIp = new GridBagConstraints();
		gbc_lblControllerIp.anchor = GridBagConstraints.WEST;
		gbc_lblControllerIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblControllerIp.gridx = 0;
		gbc_lblControllerIp.gridy = 8;
		rightPane.add(lblControllerIp, gbc_lblControllerIp);
		
		ipController = new JTextField();
		GridBagConstraints gbc_ipController = new GridBagConstraints();
		gbc_ipController.insets = new Insets(0, 0, 5, 0);
		gbc_ipController.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipController.gridx = 1;
		gbc_ipController.gridy = 8;
		rightPane.add(ipController, gbc_ipController);
		ipController.setColumns(10);
		
		JLabel PiBotTimer = new JLabel("PiBot time online:");
		GridBagConstraints gbc_PiBotTimer = new GridBagConstraints();
		gbc_PiBotTimer.anchor = GridBagConstraints.WEST;
		gbc_PiBotTimer.insets = new Insets(0, 0, 5, 5);
		gbc_PiBotTimer.gridx = 0;
		gbc_PiBotTimer.gridy = 9;
		rightPane.add(PiBotTimer, gbc_PiBotTimer);
		
		piBotTimer = new JTextField();
		piBotTimer.setEditable(false);
		piBotTimer.setText("loading...");
		GridBagConstraints gbc_piBotTimer = new GridBagConstraints();
		gbc_piBotTimer.insets = new Insets(0, 0, 5, 0);
		gbc_piBotTimer.fill = GridBagConstraints.HORIZONTAL;
		gbc_piBotTimer.gridx = 1;
		gbc_piBotTimer.gridy = 9;
		rightPane.add(piBotTimer, gbc_piBotTimer);
		piBotTimer.setColumns(10);

	}

}
