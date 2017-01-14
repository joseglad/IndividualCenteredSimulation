package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Cell extends JPanel implements Observer {
	private Color color;
	
	public Cell(Color color) {
		this.color = color;
		this.setBackground(color);
	}
	
	public void paintComponent(Graphics g) {
		Rectangle r = g.getClipBounds();
		g.setColor(this.color);
		g.fillRect(0,0,r.width,r.height);
		// sans changer la couleur, on ne voit rien !
		//g.drawString("HelloWord", 10, 10);
	}

	@Override
	public void update(Observable observable, Object observer) {
		this.setBackground(Color.black);
		this.repaint();
	}
	
	
}
