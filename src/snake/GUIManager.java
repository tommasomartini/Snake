package snake;

import snake.GameManager.Direction;


public class GUIManager {

	private static final String FOOD_CELL = "o";
	private static final String SNAKE_HEAD_N = "^";
	private static final String SNAKE_HEAD_S = "v";
	private static final String SNAKE_HEAD_E = ">";
	private static final String SNAKE_HEAD_W = "<";
	private static final String SNAKE_BODY = "x";
	private static final String VERTICAL_WALL = "|";
	private static final String HORIZONTAL_WALL = "-";
	private static final String PLAYGROUND_CELL = " ";

	private String snakeHead;

	private GameManager gameManager;
	private TerminalManager terminalManager;

	private static GUIManager guiManager;

	private GUIManager() {
		snakeHead = SNAKE_HEAD_E;
		gameManager = GameManager.getGameManager();
		terminalManager = TerminalManager.getTerminalManager();
	}

	public String draw() {

		String playGround = "";

		for (int i = 0; i < Variables.PLAYGROUND_WIDTH + 2; i++) {
			playGround = playGround + HORIZONTAL_WALL;

		}

		playGround = playGround + "\n";

		for (int h = 0; h < Variables.PLAYGROUND_HEIGTH; h++) {

			for (int w = 0; w < Variables.PLAYGROUND_WIDTH; w++) {

				if (w == 0) {
					playGround = playGround + VERTICAL_WALL;
				}

				CoordPoint actualCell = new CoordPoint(w, h);

				if (actualCell.equals(gameManager.getFoodCell())) {
					playGround = playGround + FOOD_CELL;
				}

				else if (gameManager.getSnake().contains(actualCell)) {

					if (gameManager.getSnake().getFirst().equals(actualCell)) {
						playGround = playGround + snakeHead;
					}

					else {
						playGround = playGround + SNAKE_BODY;
					}
				}

				else {
					playGround = playGround + PLAYGROUND_CELL;
				}
			}

			playGround = playGround + VERTICAL_WALL + "\n";
		}

		for (int i = 0; i < Variables.PLAYGROUND_WIDTH + 2; i++) {
			playGround = playGround + HORIZONTAL_WALL;
		}

		playGround = playGround + "\n";

		terminalManager.drawOnTerminal(playGround);
		
		gameManager.setCanMove(true);

		return playGround;
	}

	public void setSnakeHead(Direction direction) {

		switch (direction) {
		case DIRECTION_NORTH:
			snakeHead = SNAKE_HEAD_N;
			break;

		case DIRECTION_SOUTH:
			snakeHead = SNAKE_HEAD_S;
			break;

		case DIRECTION_EAST:
			snakeHead = SNAKE_HEAD_E;
			break;

		case DIRECTION_WEST:
			snakeHead = SNAKE_HEAD_W;
			break;

		default:
			break;
		}
	}

	public static GUIManager getGUIManager() {
		if (guiManager == null) 
			guiManager = new GUIManager();
		return guiManager;
	}
}
