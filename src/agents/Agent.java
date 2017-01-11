package agents;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import constants.DirectionEnum;
import constants.StateEnum;
import helpers.Coordinate;
import helpers.Empty;
import helpers.Grid;
import helpers.IDrawable;

public class Agent implements IDrawable {
	private int id = -1;
	private Coordinate coordinate;
	private int movement = 1;
	private Color color;
	private StateEnum state = StateEnum.Default;
	
	private Grid grid;
	private static int actionsNumber = 1;
	private HashMap<DirectionEnum, Object> neighborhood;
	private DirectionEnum currentDirection;
	
	private Random random;
	
	public Agent(Coordinate coordinate, Grid grid, int id) {
		this.setCoordinate(coordinate);
		this.setGrid(grid);
		this.setId(id);
	}
	
	public Agent(Coordinate coordinate, Color color, Grid grid, int id) {
		this.setCoordinate(coordinate);
		this.setColor(color);
		this.setGrid(grid);
		this.setId(id);
	}
	
	public DirectionEnum getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(DirectionEnum currentDirection) {
		this.currentDirection = currentDirection;
	}

	private Boolean isToric = false;
	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	public HashMap<DirectionEnum, Object> getneighborhood() {
		return neighborhood;
	}

	public void setneighborhood(HashMap<DirectionEnum, Object> neighborhood) {
		this.neighborhood = neighborhood;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;		
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * The behaviour when the agent meet the border or another agent
	 * @throws Exception 
	 */
	public void decide() throws Exception {
		this.checkArround();
		
		int actionChoice = this.random.nextInt(Agent.actionsNumber);
		switch (actionChoice) {
            case 0:
                for (int i = 0; i < movement; i++)
                    this.actionMove();
                break;
            case 1:
                this.actionNothing();
                break;
            default:
                //Logger.WriteLog("actionChoice selected doesn't exist or is not treated so agent decide to do nothing", LogLevelL4N.WARN);
                this.actionNothing();
                break;
        }
		
		//App.Trace(this.ToString());
	}

	private void actionNothing() {
		// Nothing to do
		
	}

	private void actionMove() throws Exception {
		int x = this.coordinate.getX();
		int y = this.coordinate.getY();
		// Choose the direction
        DirectionEnum direction = this.decideDirection();
        // Free the current position
        this.grid.free(this.coordinate);

        switch (direction)
        {
            case TopLeft:
                this.coordinate.setX(x-1);
                this.coordinate.setY(y-1);
                break;
            case Top:
                this.coordinate.setY(y-1);
                break;
            case BottomLeft:
                this.coordinate.setX(x+1);
                this.coordinate.setY(y-1);
                break;
            case Left:
                this.coordinate.setX(x-1);
                break;
            case BottomRight:
                this.coordinate.setX(x+1);
                this.coordinate.setY(y+1);
                break;
            case Bottom:
                this.coordinate.setY(y+1);
                break;
            case TopRight:
                this.coordinate.setX(x-1);
                this.coordinate.setY(y+1);
                break;
            case Right:
                this.coordinate.setX(x+1);
                break;
            case NoOne:
                break;
            default:
                //Logger.WriteLog("Unknown direction : " + direction.ToString(), LogLevelL4N.ERROR);
                break;
        }

        //Rectify Coordonate
        this.coordinate = this.rectifyCoordonate(this.isToric, this.coordinate.getX(), this.coordinate.getY());

        // Occupy the new position
        this.grid.occupy(this.coordinate, this);		
	}

	private DirectionEnum decideDirection() {
		List<DirectionEnum> possibleDirections = new LinkedList<DirectionEnum>();
		
		for(Entry<DirectionEnum, Object> entry : this.neighborhood.entrySet()) {
			DirectionEnum key = entry.getKey();
			Object value = entry.getValue();
			
			if(value != null && value instanceof Empty)
				possibleDirections.add(key);
		}

        // Si la direction actuelle est toujours possible on conitnue dans la même direction.
        if (possibleDirections.contains(this.currentDirection))
            return this.currentDirection;

        // Mélange les possibilités.
        if (possibleDirections.size() > 0)
            return this.currentDirection = possibleDirections.get(this.random.nextInt(possibleDirections.size()));

        return DirectionEnum.NoOne;
	}

	private HashMap<DirectionEnum, Object> checkArround() {
		this.neighborhood= new HashMap<DirectionEnum, Object>();
        Coordinate newCoordinate = null;

        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX(), coordinate.getY() + 1);
        neighborhood.put(DirectionEnum.Bottom, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX(), coordinate.getY() - 1);
        neighborhood.put(DirectionEnum.Top, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX() + 1, coordinate.getY());
        neighborhood.put(DirectionEnum.Right, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX() - 1, coordinate.getY());
        neighborhood.put(DirectionEnum.Left, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX() - 1, coordinate.getY() + 1);
        neighborhood.put(DirectionEnum.TopRight, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX() + 1, coordinate.getY() - 1);
        neighborhood.put(DirectionEnum.BottomLeft, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX() - 1, coordinate.getY() - 1);
        neighborhood.put(DirectionEnum.TopLeft, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));
        newCoordinate = this.rectifyCoordonate(this.isToric, coordinate.getX() + 1, coordinate.getY() + 1);
        neighborhood.put(DirectionEnum.BottomRight, this.grid.getObject(newCoordinate.getX(), newCoordinate.getY()));

        return neighborhood;
	}

	private Coordinate rectifyCoordonate(Boolean isToric, int x, int y) {
		Coordinate coord = new Coordinate(x, y);
        //if (!Torique) return coord;

        /*
        if (X <= -1)
        {
            coord.X = App.GridSizeX - 1;
        }
        else if (X >= App.GridSizeX)
        {
            coord.X = 0;
        }
        else if (Y <= -1)
        {
            coord.Y = App.GridSizeY - 1;
        }
        else if (Y >= App.GridSizeY)
        {
            coord.Y = 0;
        }
        */

        return coord;
	}
}
