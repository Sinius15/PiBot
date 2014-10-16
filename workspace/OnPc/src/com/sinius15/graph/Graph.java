package com.sinius15.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JPanel;

public class Graph extends JPanel {

	private static final long serialVersionUID = -5119700192546005044L;
	public static final Color[] COLORS = new Color[]{Color.red, Color.blue, Color.green, Color.magenta};
	
	private final int screenWidth, screenHeight;

	private final BufferedImage screen;
	public final Line[] lines;
	
	//the max value on the top.
	private int graphHeight;
	
	//the amount of values in the width.
	private int graphWidth;
	
	//the sizeFactor of the height.
	private float verticalFactor;
	
	//the resizeFactor of the widht
	private float horizontalFactor;
	
	/**
	 * 
	 * @param screenWidth de breedte van het scherm.
	 * @param screenHeight de hoogte van het scherm.
	 * @param lineAmount de hoeveelheid lijnen.
	 * @param gHeight de maximale hoogte van de grafiek. Dit zijn values, niet pixels.
	 * @param gWidth de breedte van de grafiek. Dit zijn niet pixels!
	 * @param defaultValue het getal waarmee alle lijnen geïnitalizeert moeten worden.
	 */
	public Graph(int screenWidth, int screenHeight, int lineAmount, int gHeight, int gWidth, int defaultValue) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.graphHeight = gHeight;
		this.graphWidth = gWidth;
		setPreferredSize(new Dimension(screenWidth, screenHeight));

		this.screen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		
		lines = new Line[lineAmount];
		for(int i = 0; i < lineAmount; i++){
			int[] vals = new int[gWidth];
			Arrays.fill(vals, defaultValue);
			lines[i] = new Line(COLORS[i], vals);
		}
		
		verticalFactor = screenHeight / (float)graphHeight;
		horizontalFactor = screenWidth / (float)graphWidth;
		
//		System.out.println("graph width:\t\t" + graphWidth);
//		System.out.println("graph height:\t\t" + graphHeight);
//		System.out.println("vertical factor:\t"  + verticalFactor);
//		System.out.println("horizontal factor:\t"  + horizontalFactor);
		
		
	}
	
	private void paintScreen() {
		Graphics g = screen.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, screenWidth, screenHeight);
		
		for(Line line : lines){
			g.setColor(line.color);
			for(int i = 1; i < line.data.length; i++){
				g.drawLine((int)((i-1)*horizontalFactor), (int)(screenHeight-1-line.data[i-1]*verticalFactor), 
						(int)(i*horizontalFactor), (int)(screenHeight-1-line.data[i]*verticalFactor));
			}
		}
		g.dispose();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintScreen();
		if (screen != null) {
			g.drawImage(screen, 0, 0, null);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(screenWidth, screenHeight);
	}
}
