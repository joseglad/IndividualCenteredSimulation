package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mas.MultiAgentSystem;
import views.Cell;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("MultiAgentSystem: Particules");
		//new MultiAgentSystem();
		
		int c = 25;
		JFrame frame = new JFrame("MultiAgentSystem");
		frame.setLayout(new GridLayout(c, c));
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		int nb = c*c;
		Random rand = new Random();
		while(true) {
			for(int i = 0; i < nb; i++) {
				//System.out.println(i);
				
				int r = rand.nextInt(nb);
				if(r > 4) {
					frame.getContentPane().add(new Cell(Color.blue));
				}
				else
					frame.getContentPane().add(new Cell(Color.green));
			}
			frame.getContentPane().revalidate();
			Thread.sleep(100);
			frame.getContentPane().removeAll();
			
			frame.getContentPane().repaint();
		}
		
		
	}

}
