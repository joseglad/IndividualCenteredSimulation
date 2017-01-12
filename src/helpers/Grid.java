package helpers;

import agents.Agent;
import constants.Constants;
import helpers.Coordinate;

public class Grid {
	private int xSize;
	private int ySize;
	private IDrawable[][] grid2D;
	
	public Grid(int xSize, int ySize) {
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		
		this.grid2D = new IDrawable[this.xSize][this.ySize];
		Helper.populate(this.grid2D, (IDrawable) new Empty());
	}
	
	public void occupy(Coordinate coordinate, IDrawable object) throws Exception {
		if(!(this.grid2D[coordinate.getX()][coordinate.getY()] instanceof Empty))
			throw new Exception("There is already an object to these coordinates in the grid. - " + coordinate);
		
		this.grid2D[coordinate.getX()][coordinate.getY()] = object;
	}
	
	public Object getObject(Coordinate coordinate)
    {
        return this.getObject(coordinate.getX(), coordinate.getY());
    }

    public Object getObject(int x, int y)
    {
        if (x < 0 || x > this.xSize - 1 || y < 0 || y > this.ySize - 1)
            return null;

        return this.grid2D[x][y];
    }

    public void free(Coordinate coordinate)
    {
    	if(this.getObject(coordinate) != null)
    		this.grid2D[coordinate.getX()][coordinate.getY()] = new Empty();
    }

	public Coordinate cellNumberToXYCoordinate(int cellNumber) {
		int x = cellNumber % Constants.DEFAULT_GRID_SIZE_X;
        int y = (cellNumber - x) / Constants.DEFAULT_GRID_SIZE_X;

        return new Coordinate(x, y);
	}
	
	
}
