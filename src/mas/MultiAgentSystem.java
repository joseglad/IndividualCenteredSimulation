package mas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import agents.Agent;
import constants.Constants;
import helpers.Coordinate;
import helpers.Grid;
import views.Cell;
import views.MASView;

public class MultiAgentSystem extends Observable {
	private Grid grid;
	
	private List<Agent> agents;
	private List<Integer> gridCellsNumber;
	private Timer timer = new Timer();
	private int tickNb = 0;
	private Random random = new Random();

	private JFrame frame;
	private MASView view;
	
	public MultiAgentSystem() throws Exception {
		frame = new JFrame("MultiAgentSystem");
		//frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.grid = new Grid(Constants.DEFAULT_GRID_SIZE_X, Constants.DEFAULT_GRID_SIZE_Y);
		this.agents = new LinkedList<Agent>();
		
		this.gridCellsNumber = new LinkedList<Integer>();
		for(int i = 0; i < Constants.DEFAULT_GRID_SIZE_X * Constants.DEFAULT_GRID_SIZE_Y; i++) 
			this.gridCellsNumber.add(i);
		
		int randomNumber;
        for (int i = 0; i < Constants.DEFAULT_AGENTS_NUMBER; i++) {
            randomNumber = random.nextInt(this.gridCellsNumber.size());
            int cellNumber = this.gridCellsNumber.get(randomNumber);
            this.gridCellsNumber.remove(cellNumber);

            Coordinate coordinate = this.grid.cellNumberToXYCoordinate(cellNumber);
            this.grid.occupy(coordinate, new Agent(coordinate, Color.black, this.grid, cellNumber));
            this.agents.add((Agent) this.grid.getObject(coordinate));
        }
        
        this.view = new MASView(this.grid);
        this.addObserver(this.view);
        
        JPanel myJPanel = new JPanel();
        myJPanel.setLayout(new BorderLayout());

        myJPanel.add(this.view, BorderLayout.CENTER);
        
        
        //frame.setLayout(new GridLayout(Constants.DEFAULT_GRID_SIZE_X, Constants.DEFAULT_GRID_SIZE_Y));
        frame.setContentPane(myJPanel);
        frame.setSize(new Dimension(Constants.DEFAULT_CANVAS_SIZE_X, Constants.DEFAULT_CANVAS_SIZE_Y));
        frame.setVisible(true);
        
        
        
        TimerTask task = new TimerTask() {
        	@Override
        	public void run() {
        		try {
					runSequentialy();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        };
		
        this.timer.schedule(task, 0, Constants.DEFAULT_DELAY_MILLISECONDE);
        
	}
	
	private void runSequentialy() throws Exception {
        for(Agent agent: this.agents) {
            agent.decide();
        }
        		
		this.setChanged();
		this.notifyObservers();
    }
	
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	public List<Integer> getGridCellsNumber() {
		return gridCellsNumber;
	}
	public void setGridCellsNumber(List<Integer> gridCellsNumber) {
		this.gridCellsNumber = gridCellsNumber;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public int getTickNb() {
		return tickNb;
	}
	public void setTickNb(int tickNb) {
		this.tickNb = tickNb;
	}
}
