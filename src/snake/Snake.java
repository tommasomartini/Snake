package snake;

import java.util.Timer;

import snake.GameManager.Direction;
import snake.SnakeTimerTask.GamePhase;


public class Snake {

	private static TerminalManager terminalManager;
	
	private static Timer timer;
	
	private static Timer inputTimer;
	private static InputManager inputTask;
	
	private static SnakeTimerTask timerTask;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		terminalManager = TerminalManager.getTerminalManager();

		timerTask = new SnakeTimerTask();
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, Variables.MOVE_DELAY, Variables.MOVE_PERIOD);
		
		inputTask = new InputManager();
		inputTimer = new Timer();
		inputTimer.schedule(inputTask, Variables.MOVE_DELAY, Variables.MOVE_PERIOD / 2);
	}
	
	public static void newGame() {
		
		GUIManager.getGUIManager().setSnakeHead(Direction.DIRECTION_EAST);
		
		timer.cancel();
		timer.purge();
		
		GameManager.getGameManager().reset();
		
		timerTask = new SnakeTimerTask();
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, Variables.MOVE_DELAY, Variables.MOVE_PERIOD);
		
		inputTask = new InputManager();
		inputTimer = new Timer();
		inputTimer.schedule(inputTask, Variables.MOVE_DELAY, Variables.MOVE_PERIOD / 2);
	}
	
	public static void pause() {
		timerTask.setCurrentPhase(GamePhase.GAME_PHASE_PAUSE);
		terminalManager.pauseGame();
	}
	
	public static void resume() {
		timerTask.setCurrentPhase(GamePhase.GAME_PHASE_PLAYING);
	}

	public static void stopGame() {
		timerTask.setCurrentPhase(GamePhase.GAME_PHASE_END);
		terminalManager.endGame();
	}
	
	public static void quitGame() {
		timer.cancel();
		timer.purge();
		
		inputTimer.cancel();
		inputTimer.purge();
		
		System.exit(0);
	}
}
