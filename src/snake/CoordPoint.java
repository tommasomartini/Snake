package snake;

public class CoordPoint {
	
	private int coordX;
	private int coordY;
	
	public CoordPoint(int coordX, int coordY) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	@Override
	public String toString() {
		return "X: " + coordX + " Y: " + coordY;
	}

	@Override
	public boolean equals(Object obj) {
		CoordPoint point = (CoordPoint) obj;
		return (point.getCoordX() == coordX && point.getCoordY() == coordY);
	}
}
