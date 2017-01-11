package helpers;

public class Coordinate {
	private int x = Integer.MIN_VALUE;
	private int y = Integer.MIN_VALUE;
	
	public Coordinate(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
	
	/**
	 * This ToString is do simply to cast the object in a string formated in Json
	 * @return
	 */
	public String toString() {
		String json = "{";
		json += "\"x\": \""+x+"\"";
		json += "\"y\": \""+y+"\"";
		json += "}";
		return json;
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
