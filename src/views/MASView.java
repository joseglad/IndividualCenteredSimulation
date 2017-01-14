package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import agents.Agent;
import constants.Constants;
import helpers.Empty;
import helpers.Grid;
import helpers.IDrawable;

public class MASView extends JPanel implements Observer {
	private Grid grid;
	private int boxSize = Constants.DEFAULT_BOX_SIZE;
	private int test = 0;
	
	public MASView(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = Constants.DEFAULT_CANVAS_SIZE_X;
		int height = Constants.DEFAULT_CANVAS_SIZE_Y;
		
		for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	IDrawable instance = (IDrawable) this.grid.getObject(x, y);
                if (instance instanceof Agent) {
                    Agent agent = (Agent) instance;
                	g.setColor(agent.getColor());
                } else {
                	g.setColor(Color.white);
                }
                //g.fill3DRect(x * boxSize, y * boxSize, boxSize, boxSize, true);
                g.fillRect(x * boxSize, y * boxSize, boxSize, boxSize);
            }
        }
		test++;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
}
