package com.sinius15.pibot.pi;

public class Alg {
	
	float whenToStop = -999;
	int minSpeed = 60;

	
	public void doAlgoritme(PiServer s){
		
		float diff = s.pich - s.nullPoint;
		if(diff+1 > whenToStop && diff-1 < whenToStop){
			s.setLeftWheelSpeed(0);
			s.setRightWheelSpeed(0);
			whenToStop = -999;
			
		}
		
		if(diff < -2 && whenToStop == -999){
			s.setLeftWheelSpeed(minSpeed);
			s.setRightWheelSpeed(minSpeed);
			whenToStop = diff/2;
		}
		if(diff > 2 && whenToStop == -999){
			s.setLeftWheelSpeed(-minSpeed);
			s.setRightWheelSpeed(-minSpeed);
			whenToStop = diff/2;
		}
		

	}
	
}
