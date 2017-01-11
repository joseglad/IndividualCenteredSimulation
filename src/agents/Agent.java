package agents;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import constants.DirectionEnum;
import constants.StateEnum;
import helpers.Coordinate;
import helpers.Grid;
import helpers.IDrawable;

public class Agent implements IDrawable {
	private int id;
	private Coordinate coordinate;
	private int movement = 1;
	private Color color;
	private StateEnum state = StateEnum.Default;
	
	private Grid grid;
	private static int actionsNumber = 1;
	private HashMap<DirectionEnum, Object> neighborhood;
	
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

	private Random random;
	
	public Agent(Coordinate coordinate, int id) {
		this.setCoordinate(coordinate);
		this.setId(id);
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
	 */
	public void Decide() {
		this.checkArround();
		
		int actionChoice = this.random.nextInt(this.actionsNumber);
		switch (actionChoice)
        {
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
		// TODO Auto-generated method stub
		
	}

	private void actionMove() {
		// TODO Auto-generated method stub
		
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
