package com.sinius15.graph;

import java.awt.Color;

public class Line {

	public Color color;
	public int[] data;
	
	private int maxValue = 0;
	private int valueAmount;

	public Line(Color color, int[] data) {
		if(color == null || data == null || data.length == 0){
			throw new IllegalArgumentException(
					"Everything should be filled with at least something. NOT NULL!");
		}
		for(int i : data){
			if(i > maxValue)
				maxValue = i;
		}
		valueAmount = data.length;
		this.color = color;
		this.data = data;
	}

	public int getMaxValue() {
		return maxValue;
	}
	
	public void setData(int[] newData){
		if(newData.length != valueAmount)
			throw new IllegalArgumentException(
					"The lenght of the newData array should be the same as the old one.");
		data = newData;
	}

}
