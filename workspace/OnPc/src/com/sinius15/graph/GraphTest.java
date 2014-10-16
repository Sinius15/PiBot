package com.sinius15.graph;

import java.util.Arrays;

import com.sinius15.pibot.pc.Controller;

public class GraphTest {

	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		ArrayList<Line> lines = new ArrayList<>();
//		lines.add(new Line(Color.blue, new int[]{0, 10, 20, 30, 40, 50, 60, 70, 80, 90}));
//		lines.add(new Line(Color.red, new int[]{101, 90, 80, 30, 10, 0, 0, 50, 60, 1}));
//		
//		Graph g = new Graph(lines, 50, 50);
//		
//		frame.setContentPane(g);
//		frame.pack();
//		
//		g.repaint();
//		
		
		int[] a = new int[]{1, 2, 3, 4, 5};
		int[] b = Controller.addOnEnd(a, 6);
		System.out.println(Arrays.toString(b));
	}
	
}
