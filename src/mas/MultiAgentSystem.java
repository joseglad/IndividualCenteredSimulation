package mas;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import agents.Agent;
import constants.Constants;
import helpers.Coordinate;
import helpers.Grid;

public class MultiAgentSystem {
	private Grid grid;
	
	private List<Agent> agents;
	private List<Integer> gridCellsNumber;
	private Timer timer = new Timer();
	private int tickNb = 0;
	private Random random = new Random();
	
	private int counter = 1;
	
	public MultiAgentSystem() throws Exception {
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
        
        TimerTask task = new TimerTask() {
        	@Override
        	public void run() {
        		try {
					runSequentialy();
					System.out.println(counter);
					counter++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        };
		
        this.timer.schedule(task, 0, Constants.DEFAULT_DELAY_MILLISECONDE);
	}
	
	private void runSequentialy() throws Exception
    {
        for(Agent agent: this.agents) {
            agent.decide();
        }
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
