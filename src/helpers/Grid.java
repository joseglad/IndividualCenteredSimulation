package helpers;

import agents.Agent;

public class Grid {
	private int xSize;
	private int ySize;
	private Object[][] grid2D;
	
	public Grid(int xSize, int ySize) {
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		
		this.grid2D = new Object[this.xSize][this.ySize];
		Helper.populate((Agent[][]) this.grid2D, new Empty());
	}
	
	public void occupy(Coordinate coordinate, Object object) throws Exception {
		if(this.grid2D[coordinate.getX()][coordinate.getY()] instanceof Empty)
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
	
	
}
