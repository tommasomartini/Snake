package snake;

import java.util.LinkedList;

/**
 * 
 */
public class GameManager {

	public enum Direction {
		DIRECTION_NORTH,
		DIRECTION_SOUTH,
		DIRECTION_EAST,
		DIRECTION_WEST;
	}
	
	private static GameManager gameManager;

	/**
	 * 
	 */
	private LinkedList<CoordPoint> snake;

	/**
	 * 
	 */
	private CoordPoint nextCell;
	private CoordPoint foodCell;

	private Direction currentDirection;
	
	private int counter;
	
	private boolean canMove = false;

	/**
	 * 
	 */
	private GameManager() {
		
		reset();
	}

	public void move() {
		
		if(snake.contains(nextCell)) {
			Snake.stopGame();
			return;
		}
			
		if(nextCell.equals(foodCell)) {
			foodCell = getNewFoodCell();
			counter++;
		}
			
		else if (!nextCell.equals(foodCell))
			snake.removeLast();
		
		snake.addFirst(nextCell);

		int newX = nextCell.getCoordX();
		int newY = nextCell.getCoordY();
		
		switch (currentDirection) {
		case DIRECTION_NORTH:
			newY -= 1;
			
			if (newY == -1)
				newY = Variables.PLAYGROUND_HEIGTH - 1;
			
			break;

		case DIRECTION_SOUTH:
			newY += 1;
			
			if (newY == Variables.PLAYGROUND_HEIGTH)
				newY = 0;
			
			break;

		case DIRECTION_EAST:
			newX += 1;
			
			if (newX == Variables.PLAYGROUND_WIDTH)
				newX = 0;
			
			break;

		case DIRECTION_WEST:
			newX -= 1;
			
			if (newX == -1)
				newX = Variables.PLAYGROUND_WIDTH - 1;
			
			break;

		default:
			break;
		}
		
		nextCell = new CoordPoint(newX, newY);
	}
	
	private CoordPoint getNewFoodCell() {
		
		CoordPoint newFoodCell = null;
		
		do {
			int x = (int) Math.abs(Math.random() * 10);
			int y = (int) Math.abs(Math.random() * 10);
			newFoodCell = new CoordPoint(x, y);
		} while ((foodCell != null && foodCell.equals(newFoodCell)) || snake.contains(newFoodCell));
		
		return newFoodCell;
	}
	
	public void turn(Direction direction) {
		
		boolean validDirection = false;
		
		switch (currentDirection) {
		case DIRECTION_NORTH:
			
			if (direction != Direction.DIRECTION_NORTH && direction != Direction.DIRECTION_SOUTH)
				validDirection = true;
			
			break;

		case DIRECTION_SOUTH:
			
			if (direction != Direction.DIRECTION_NORTH && direction != Direction.DIRECTION_SOUTH)
				validDirection = true;

			break;

		case DIRECTION_EAST:
			
			if (direction != Direction.DIRECTION_EAST && direction != Direction.DIRECTION_WEST)
				validDirection = true;

			break;

		case DIRECTION_WEST:
			
			if (direction != Direction.DIRECTION_EAST && direction != Direction.DIRECTION_WEST)
				validDirection = true;

			break;

		default:
			break;
		}
		
		if (validDirection && canMove) {
			canMove = false;
			currentDirection = direction;
			GUIManager.getGUIManager().setSnakeHead(direction);
		}
	}
	
	public void reset() {
		
		counter = 0;
		
		snake = new LinkedList<CoordPoint>();

		snake.addLast(new CoordPoint(4, 0));
		snake.addLast(new CoordPoint(3, 0));
		snake.addLast(new CoordPoint(2, 0));
		snake.addLast(new CoordPoint(1, 0));
		snake.addLast(new CoordPoint(0, 0));

		nextCell = new CoordPoint(5, 0);
		foodCell = getNewFoodCell();

		currentDirection = Direction.DIRECTION_EAST;
	}

	public LinkedList<CoordPoint> getSnake() {
		return snake;
	}

	public CoordPoint getFoodCell() {
		return foodCell;
	}

	public int getCounter() {
		return counter;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public static GameManager getGameManager() {
		if (gameManager == null) {
			gameManager = new GameManager();
		}
		
		return gameManager;
	}
}
